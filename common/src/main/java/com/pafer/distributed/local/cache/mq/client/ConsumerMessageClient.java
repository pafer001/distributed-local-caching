package com.pafer.distributed.local.cache.mq.client;

import com.pafer.distributed.local.cache.mq.LocalCacheConsumer;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumerMessageClient extends AbstractMessageClient {

    private static Channel channel;
    protected int prefetchCount;

    public void consumer() throws IOException {
        if (channel == null) {
            synchronized (channel) {
                if (channel == null) {
                    createChannel();
                }
            }
        }
        channel.basicQos(prefetchCount);
        channel.queueBind(queueName, exchangeName, routingKey);
        channel.basicConsume(queueName, true, new LocalCacheConsumer(channel) );
    }
}
