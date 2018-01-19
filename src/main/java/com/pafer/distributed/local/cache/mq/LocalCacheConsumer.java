package com.pafer.distributed.local.cache.mq;

import com.pafer.distributed.local.cache.LocalCacheHandler;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class LocalCacheConsumer extends DefaultConsumer {


    private LocalCacheHandler localCacheHandler = new LocalCacheHandler();

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
