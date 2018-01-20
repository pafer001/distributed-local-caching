package com.pafer.distributed.local.cache.mq.client;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author wangzhenya
 */
public abstract class AbstractMessageClient {

    protected String user;
    protected String pwd;
    protected String vHost;
    protected String queueName;
    protected String exchangeName = "lc-exchange";
    protected String routingKey = "lc-rk";
    protected Address[] address;

    protected Connection createConnect() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(user);
        factory.setPassword(pwd);
        factory.setVirtualHost(vHost);
        factory.setRequestedHeartbeat(60);
        //一台机器停了，会链接其他的机器
        factory.setAutomaticRecoveryEnabled(true);
        return factory.newConnection(address);
    }


    protected Channel createChannel() throws IOException {

       return createConnect().createChannel();
    }
}
