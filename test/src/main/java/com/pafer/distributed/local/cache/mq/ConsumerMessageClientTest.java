package com.pafer.distributed.local.cache.mq;

import com.pafer.distributed.local.cache.lc.LocalCacheConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author wangzhenya
 */
public class ConsumerMessageClientTest {

    private ConsumerMessageClient consumerMessageClient;

    @Before
    public void initProductMessageClient() throws IOException {

        LocalCacheClientConfiguration configuration = new LocalCacheClientConfiguration();
        configuration.setAddress("test2.sns.sohuno.com:5672");
        configuration.setvHost("/sns_dev");
        configuration.setUser("sns");
        configuration.setPwd("snstest");
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
