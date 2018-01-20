package com.pafer.distributed.local.cache.lc;

import com.google.common.base.Function;

/**
 * 查询的时候，才put
 *
 * @author wangzhenya
 */
public class LocalCacheHandler {

    private static LocalCache cache = new LocalCache<String, Object>();

    public static LocalCache getCache() {
        return cache;
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
