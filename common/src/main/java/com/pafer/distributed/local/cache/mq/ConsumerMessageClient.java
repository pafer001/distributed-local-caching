package com.pafer.distributed.local.cache.mq;

import com.pafer.distributed.local.cache.lc.LocalCacheHandler;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumerMessageClient extends AbstractMessageClient {

    private static Channel channel;

    protected int prefetchCount;

    private LocalCacheHandler localCacheHandler;

    public int getPrefetchCount() {
        return prefetchCount;
    }

    public void setPrefetchCount(int prefetchCount) {
        this.prefetchCount = prefetchCount;
    }



    public ConsumerMessageClient(LocalCacheHandler localCacheHandler,
                                 LocalCacheClientConfiguration localCacheClientConfiguration) throws IOException{

        if (channel == null) {
            synchronized (this) {
                if (channel == null) {
                    channel = createChannel();
                }
            }
        }

        this.localCacheHandler = localCacheHandler;
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


    class LocalCacheConsumer extends DefaultConsumer {



        public LocalCacheConsumer(Channel channel) {
            super(channel);
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope,
                                   AMQP.BasicProperties properties, byte[] body) throws IOException {

            String key = new String(body);
            localCacheHandler.remove(key);
        }
    }
}

