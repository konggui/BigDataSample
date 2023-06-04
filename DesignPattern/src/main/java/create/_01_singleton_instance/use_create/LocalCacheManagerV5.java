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
public class LocalCacheManagerV5 implements ILocalCache {
    public static final Logger LOG = LoggerFactory.getLogger(LocalCacheManagerV5.class);
    public static LocalCacheManagerV5 localCacheManagerV5 = null;
    private static Cache<String, String> guavaCache = null;

    private LocalCacheManagerV5() {
        guavaCache = CacheBuilder.newBuilder().build();
    }

    public static synchronized LocalCacheManagerV5 getInstance() {
        if (null == localCacheManagerV5) {
            synchronized (LocalCacheManagerV5.class) {
                // 加锁 线程安全， 双重判断 高效
                if (null == localCacheManagerV5)  {
                    localCacheManagerV5 = new LocalCacheManagerV5();
                }
            }
        }
        return localCacheManagerV5;
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
