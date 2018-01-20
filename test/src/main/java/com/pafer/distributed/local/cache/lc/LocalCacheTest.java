package com.pafer.distributed.local.cache.lc;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LocalCacheTest {

    LocalCache<String, String> cache = null;

    @Before
    public void init() {

        cache = new LocalCache<String, String>();

    }

    @Test
    public void testPutSuccess() {
        String key = "key-" + System.currentTimeMillis();
        String value = "value-" + System.currentTimeMillis();

        cache.put(key, value);

        String cacheValue = cache.get(key);
        Assert.assertEquals(value, cacheValue);
    }

    @Test
    public void testRemoveSuccess() {
        String key = "key-" + System.currentTimeMillis();
        String value = "value-" + System.currentTimeMillis();

        cache.put(key, value);
        cache.remove(key);
        String cacheValue = cache.get(key);
        Assert.assertNull(cacheValue);
        Assert.assertNotEquals(value, cacheValue);
    }

}
