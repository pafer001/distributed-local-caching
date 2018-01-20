package com.pafer.distributed.local.cache.lc;

import com.google.common.base.Function;

/**
 * 查询的时候，才put
 *
 * @author wangzhenya
 */
public class LocalCacheHandler {

    //cache own by LocalCacheHandler class
    private static LocalCache cache;


    public LocalCacheHandler(LocalCacheConfiguration cacheConfiguration) {
        cache = new LocalCache(cacheConfiguration.getCacheMaxSize(),
                cacheConfiguration.getCacheExpireSecond(), cacheConfiguration.getCachePolicy());
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public Object get(String key, Function<String, Object> function) {
        Object o = cache.get(key);
        if (o != null) {
            return o;
        }
        o = function.apply(key);
        if (o != null) {
            cache.put(key, o);
        }
        return o;
    }

    public Object getIfPresent(String key) {
        return cache.get(key);
    }
}
