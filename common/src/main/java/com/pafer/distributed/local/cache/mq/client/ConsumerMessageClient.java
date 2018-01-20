package com.pafer.distributed.local.cache.mq.client;

import com.pafer.distributed.local.cache.mq.LocalCacheConsumer;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumerMessageClient extends AbstractMessageClient {

    private static Channel channel;

    protected int prefetchCount;

    public int getPrefetchCount() {
        return prefetchCount;
    }

    public void setPrefetchCount(int prefetchCount) {
        this.prefetchCount = prefetchCount;
    }

    public ConsumerMessageClient() throws IOException{

        if (channel == null) {
            synchronized (this) {
                if (channel == null) {
                    channel = createChannel();
                }
            }
        }
    }

    public void consumer() throws IOException {
        if (channel == null) {
            synchronized (this) {
                if (channel == null) {
                    channel = createChannel();
                }
            }
        }
        channel.basicQos(prefetchCount);
        channel.queueBind(queueName, exchangeName, routingKey);
        channel.basicConsume(queueName, true, new LocalCacheConsumer(channel) );
    }
}
