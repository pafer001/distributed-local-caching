package com.pafer.distributed.local.cache.mq;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ProductMessageClient extends MessageClient {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
            5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1000),
            Executors.defaultThreadFactory(), new LocalCacheRejectedExecutionHandler()
    );

    private static Channel channel;

    public void initProducer() throws IOException {

        channel = createChannel();
        channel.exchangeDeclare(exchangeName, "fanout", true);
    }

    public void sendMessage(Object object) throws IOException {

        if (object == null) {
            return;
        }
        Gson gson = new Gson();
        channel.basicPublish(exchangeName, routingKey,
                MessageProperties.PERSISTENT_TEXT_PLAIN,   gson.toJson(object).getBytes());
    }

}
