package com.pafer.distributed.local.cache.mq;

import com.google.common.base.Strings;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author pafer
 */
public class ProductMessageClient extends AbstractMessageClient {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
            5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1000),
            Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }
    );

    private static Channel channel;

    public ProductMessageClient(LocalCacheClientConfiguration configuration) throws IOException {
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

    /**
     * send delete lc message
     *
     * @param key
     */
    public void sendRemoveDataMessage(String key) throws IOException {
        sendMessage(key);
    }

}
