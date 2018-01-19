package com.pafer.distributed.local.cache.mq;


import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by pafer on 17-6-1.
 */
public class LocalCacheRejectedExecutionHandler implements RejectedExecutionHandler {

    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    }
}
