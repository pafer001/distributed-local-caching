package com.pafer.distributed.local.cache.lc;


import com.pafer.distributed.local.cache.tools.DayTime;

public class LocalCacheConfiguration {

    private int cacheMaxSize = 12000;;
    private long cacheExpireSecond = DayTime.HOUR;;
    private int cachePolicy = 1;

    public int getCacheMaxSize() {
        return cacheMaxSize;
    }

    public void setCacheMaxSize(int cacheMaxSize) {
        this.cacheMaxSize = cacheMaxSize;
    }

    public long getCacheExpireSecond() {
        return cacheExpireSecond;
    }

    public void setCacheExpireSecond(long cacheExpireSecond) {
        this.cacheExpireSecond = cacheExpireSecond;
    }

    public int getCachePolicy() {
        return cachePolicy;
    }

    public void setCachePolicy(int cachePolicy) {
        this.cachePolicy = cachePolicy;
    }
}
