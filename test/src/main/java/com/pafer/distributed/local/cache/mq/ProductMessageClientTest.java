package com.pafer.distributed.local.cache.mq;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author pafer
 */
public class ProductMessageClientTest {

    private ProductMessageClient productMessageClient;

    @Before
    public void initProductMessageClient() throws IOException{

        LocalCacheClientConfiguration configuration = new LocalCacheClientConfiguration();
        configuration.setAddress("128.0.0.1:5672");
        configuration.setvHost("/dev");
        configuration.setUser("user");
        configuration.setPwd("pwd");
        productMessageClient = new ProductMessageClient(configuration);
    }

    @Test
    public void testSendRemoveDataMessage() throws IOException {

        while (true) {
            productMessageClient.sendRemoveDataMessage("111");
        }
    }


}
