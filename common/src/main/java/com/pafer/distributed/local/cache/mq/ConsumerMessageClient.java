package com.pafer.distributed.local.cache.mq;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.pafer.distributed.local.cache.lc.LocalCacheConfiguration;
import com.pafer.distributed.local.cache.lc.LocalCacheHandler;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.*;

/**
 * @author wangzhenya
 */
public class ConsumerMessageClient extends AbstractMessageClient {

    private static Channel channel;

    protected int qosCount;

    private static LocalCacheHandler localCacheHandler;
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
            5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1000),
            Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }
    );
    public ConsumerMessageClient(LocalCacheConfiguration localCacheConfiguration,
                                 LocalCacheClientConfiguration configuration) throws IOException {
        if (configuration == null) {
            throw new NullPointerException(" localCacheClientConfiguration is null");
        }

        String address = configuration.getAddress();
        if (Strings.isNullOrEmpty(address)) {
            throw new NullPointerException("localCacheClientConfiguration address is null");
        }
        this.user = configuration.getUser();
        this.pwd = configuration.getPwd();
        this.vHost = configuration.getvHost();
        this.address = Address.parseAddresses(address);
        this.queueName = Joiner.on("/").join(Lists.newArrayList(configuration.getQueueName(),
                InetAddress.getLocalHost().getHostAddress().toString()));
        this.qosCount = configuration.getQosCount();

        if (channel == null) {
            synchronized (this) {
                if (channel == null) {
                    channel = createChannel();
                }
            }
        }

        if (localCacheHandler == null) {
            synchronized (this) {
                if (localCacheHandler == null) {
                    localCacheHandler =  new LocalCacheHandler(localCacheConfiguration);
                }
            }
        }


    }

    public int getQosCount() {
        return qosCount;
    }

    public void setQosCount(int qosCount) {
        this.qosCount = qosCount;
    }

    public void consumer() throws IOException {
        if (channel == null) {
            synchronized (this) {
                if (channel == null) {
                    channel = createChannel();
                }
            }
        }

        channel.basicQos(qosCount);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        channel.basicConsume(queueName, true, new LocalCacheConsumer(channel));
    }

    class LocalCacheConsumer extends DefaultConsumer {

        public LocalCacheConsumer(Channel channel) {
            super(channel);
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope,
                                   AMQP.BasicProperties properties, final byte[] body) throws IOException {
            threadPoolExecutor.execute(new Runnable() {
                public void run() {
                    String key = new String(body);
                    System.out.println("consumer---" + key);
                    localCacheHandler.remove(key);
                }
            });

        }
    }
}

