package create._01_singleton_instance.use_create;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import create._01_singleton_instance.pre_create.ILocalCache;


/**
 * @desc:
 * @program: MapPOI
 * @packagename: create.singleton_instance.pre_create
 * @author: aishuang
 * @date: 2023-04-10 03:37
 */
public class LocalCacheManagerV4 implements ILocalCache {
    public static final Logger LOG = LoggerFactory.getLogger(LocalCacheManagerV4.class);
    public static LocalCacheManagerV4 localCacheManagerV4 = null;
    private static Cache<String, String> guavaCache = null;

    private LocalCacheManagerV4() {
        guavaCache = CacheBuilder.newBuilder().build();
    }

    public static synchronized LocalCacheManagerV4 getInstance() {
        if (null == localCacheManagerV4) {
            // 线程安全，但低效
            localCacheManagerV4 = new LocalCacheManagerV4();
        }
        return localCacheManagerV4;
    }

    @Override
    public void put(String key, String value) {
        guavaCache.put(key, value);
    }

    @Override
    public String get(String key) {
        return guavaCache.getIfPresent(key);
    }
}
