package com.pafer.distributed.local.cache.mq;

import com.google.common.base.Strings;
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
    protected String routingKey = "lc-routingKey";
    protected Address[] address;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getvHost() {
        return vHost;
    }

    public void setvHost(String vHost) {
        this.vHost = vHost;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Address[] getAddress() {
        return address;
    }

    public void setAddress(Address[] address) {
        this.address = address;
    }

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
