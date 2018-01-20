package com.pafer.distributed.local.cache.mq.client;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.*;

public class ProductMessageClient extends AbstractMessageClient {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
            5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1000),
            Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }
    );

    private static Channel channel;

    private void initProducer() throws IOException {

        channel = createChannel();
        channel.exchangeDeclare(exchangeName, "fanout", true);
    }

    public void sendMessage(Object object) throws IOException {

        if (channel == null) {
            synchronized (this) {
                if (channel == null) {
                    initProducer();
                }
            }
        }
        if (object == null) {
            return;
        }
        Gson gson = new Gson();
        channel.basicPublish(exchangeName, routingKey,
                MessageProperties.PERSISTENT_TEXT_PLAIN, gson.toJson(object).getBytes());
    }

}
