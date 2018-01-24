package com.pafer.distributed.local.cache.mq;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author wangzhenya
 */
public class ProductMessageClientTest {

    private ProductMessageClient productMessageClient;

    @Before
    public void initProductMessageClient() throws IOException{

        LocalCacheClientConfiguration configuration = new LocalCacheClientConfiguration();
        configuration.setAddress("test2.sns.sohuno.com:5672");
        configuration.setvHost("/sns_dev");
        configuration.setUser("sns");
        configuration.setPwd("snstest");
        productMessageClient = new ProductMessageClient(configuration);
    }

    @Test
    public void testSendRemoveDataMessage() throws IOException {

        while (true) {
            productMessageClient.sendRemoveDataMessage("111");
        }
    }


}
