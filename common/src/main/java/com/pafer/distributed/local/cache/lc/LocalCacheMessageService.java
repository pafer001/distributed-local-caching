package com.pafer.distributed.local.cache.lc;

import com.pafer.distributed.local.cache.mq.client.ProductMessageClient;

import java.io.IOException;

/**
 * @author wangzhenya
 */
public class LocalCacheMessageService {

    private ProductMessageClient  productMessageClient = new ProductMessageClient();

    private LocalCacheHandler handler = new LocalCacheHandler();

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
