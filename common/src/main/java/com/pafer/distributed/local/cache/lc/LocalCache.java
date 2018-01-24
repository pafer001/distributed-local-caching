package com.pafer.distributed.local.cache.lc;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.pafer.distributed.local.cache.tools.DayTime;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * 和consumer存在的
 * @param <V> value
 * @author wangzhenya
 */
public class LocalCache<String, V> {

    /**
     * cache store data
     */
    public Cache<String, V> cache;
    /**
     * cache max length
     */
    private int max = 12000;
    /**
     * every k-v expire after expireSecond;
     * default is one hour
     */
    private long expireSecond = DayTime.HOUR;

    /**
     * 1：expire after access, else expire after write
     */
    private int policy = 1;

    public LocalCache() {

        if (this.policy == 1) {
            cache = CacheBuilder.newBuilder().maximumSize(this.max)
                    .expireAfterAccess(this.expireSecond, TimeUnit.SECONDS).build();
        } else {
            cache = CacheBuilder.newBuilder().maximumSize(this.max)
                    .expireAfterWrite(this.expireSecond, TimeUnit.SECONDS).build();
        }
    }


    public LocalCache(int max, long expireSecond, int policy) {
        this.max = max;
        this.expireSecond = expireSecond;
        this.policy = policy;

        if (this.policy == 1) {
            cache = CacheBuilder.newBuilder().maximumSize(this.max)
                    .expireAfterAccess(this.expireSecond, TimeUnit.SECONDS).build();
        } else {
            cache = CacheBuilder.newBuilder().maximumSize(this.max)
                    .expireAfterWrite(this.expireSecond, TimeUnit.SECONDS).build();
        }
    }

    public void put(String k, V v) {
        if (k == null || v == null) {
            return;
        }
        cache.put(k, v);
    }

    public void remove(String k) {
        if (k == null) {
            return;
        }
        cache.invalidate(k);
    }

    public V get(String k) {
        if (k == null) {
            return null;
        }
        return cache.getIfPresent(k);
    }

    public long size() {
        return cache.size();
    }

    public ConcurrentMap<String, V> all() {
        return cache.asMap();
    }

    public Set<String> keySet() {
        return cache.asMap().keySet();
    }

    public void putAll(LocalCache<String, V> snsLocalCache) {
        if (cache == null || snsLocalCache == null) {
            return;
        }
        this.cache.putAll(snsLocalCache.cache.asMap());
    }

    public void putAll(Map<String, V> map) {
        if (cache == null || map == null) {
            return;
        }
        this.cache.putAll(map);
    }
}
