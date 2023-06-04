package create._01_singleton_instance.use_create;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import create._01_singleton_instance.pre_create.ILocalCache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @desc:
 * @program: MapPOI
 * @packagename: create.singleton_instance.pre_create
 * @author: aishuang
 * 
 * @date: 2023-04-10 03:37
 */
public class LocalCacheManagerV6 implements ILocalCache {
    public static final Logger LOG = LoggerFactory.getLogger(LocalCacheManagerV6.class);
    public static LocalCacheManagerV6 localCacheManagerV6 = null;
    private static Cache<String, String> guavaCache = null;
    private static final Lock reetrantLock = new ReentrantLock();

    private LocalCacheManagerV6() {
        guavaCache = CacheBuilder.newBuilder().build();
    }

    public static synchronized LocalCacheManagerV6 getInstance() {
        if (null == localCacheManagerV6) {
            reetrantLock.lock();
            try {
                // lock加锁 线程安全， 双重判断 高效
                if (null == localCacheManagerV6)  {
                    localCacheManagerV6 = new LocalCacheManagerV6();
                }
            } finally {
                reetrantLock.unlock();
            }
        }
        return localCacheManagerV6;
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
