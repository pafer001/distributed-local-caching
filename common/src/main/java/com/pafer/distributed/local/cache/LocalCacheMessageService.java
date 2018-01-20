package com.pafer.distributed.local.cache;

import com.pafer.distributed.local.cache.lc.LocalCacheHandler;
import com.pafer.distributed.local.cache.mq.client.ProductMessageClient;

import java.io.IOException;

/**
 * @author wangzhenya
 */
public class LocalCacheMessageService {

    private ProductMessageClient  productMessageClient ;

    private LocalCacheHandler handler ;

    public LocalCacheMessageService() throws IOException {

        productMessageClient = new ProductMessageClient();
        handler = new LocalCacheHandler();
    }

    /**
     * send delete lc message
     * @param key
     */
    public void sendRemoveDataMessage(String key) throws IOException{
        handler.remove(key);
        productMessageClient.sendMessage(key);
    }

    /**
     * consumer remove data message
     */
    public void consumeRemoveDataMessage(String key) {
        handler.remove(key);
    }
}
