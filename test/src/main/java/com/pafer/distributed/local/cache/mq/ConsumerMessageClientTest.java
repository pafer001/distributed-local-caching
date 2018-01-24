package com.pafer.distributed.local.cache.mq;

import com.pafer.distributed.local.cache.lc.LocalCacheConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author pafer
 */
public class ConsumerMessageClientTest {

    private ConsumerMessageClient consumerMessageClient;

    @Before
    public void initProductMessageClient() throws IOException {

        LocalCacheClientConfiguration configuration = new LocalCacheClientConfiguration();
        configuration.setAddress("128.0.0.1:5672");
        configuration.setvHost("/dev");
        configuration.setUser("user");
        configuration.setPwd("pwd");
        configuration.setQueueName("lc-queue");
        configuration.setQosCount(5);

        consumerMessageClient = new ConsumerMessageClient(new LocalCacheConfiguration(), configuration);
    }

    @Test
    public void testConsumer() throws IOException {


        consumerMessageClient.consumer();

        try {
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
