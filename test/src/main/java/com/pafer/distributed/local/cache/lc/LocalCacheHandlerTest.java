package com.pafer.distributed.local.cache.lc;

import com.google.common.base.Function;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nullable;

public class LocalCacheHandlerTest {

    private LocalCacheHandler localCacheHandler = null;
    @Before
    public void init() {
        LocalCacheConfiguration localCacheConfiguration = new LocalCacheConfiguration();
        localCacheHandler = new LocalCacheHandler(localCacheConfiguration);
    }

    @Test
    public void testGetIfPresent() {
        String key = "key-" + System.currentTimeMillis();
        Object ifPresent = localCacheHandler.getIfPresent(key);
        Assert.assertNull(ifPresent);
    }

    @Test
    public void testGet() {
        String key = "key-" + System.currentTimeMillis();
        final Object ifPresent = localCacheHandler.get(key, new Function<String, Object>() {

            @Nullable
            public Object apply(@Nullable String s) {
                return "value-" + System.currentTimeMillis();
            }
        });

        Assert.assertNotNull(ifPresent);
        Assert.assertEquals(localCacheHandler.getIfPresent(key), ifPresent);
    }
}
