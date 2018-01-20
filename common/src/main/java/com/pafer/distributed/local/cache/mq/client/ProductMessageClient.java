package com.pafer.distributed.local.cache.mq.client;

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


    public ProductMessageClient(LocalCacheClientConfiguration localCacheClientConfiguration) throws IOException {
        if (channel == null) {
            synchronized (this) {
                if (channel == null) {
                    channel = createChannel();
                    channel.exchangeDeclare(exchangeName, "fanout", true);
                }
            }
        }
    }

    public void sendMessage(final String key) throws IOException {

        threadPoolExecutor.execute(new Runnable() {
            public void run() {
                try {
                    channel.basicPublish(exchangeName, routingKey,
                            MessageProperties.PERSISTENT_TEXT_PLAIN, key.getBytes());
                } catch (Exception e) {

                }

            }
        });

    }

}
