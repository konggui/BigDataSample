package create._01_singleton_instance.use_create;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import create._01_singleton_instance.pre_create.ILocalCache;


/**
 * @desc: 静态内部类
 * @program: MapPOI
 * @packagename: create.singleton_instance.pre_create
 * @author: aishuang
 * @date: 2023-04-10 03:37
 */
public class LocalCacheManagerV7 implements ILocalCache {
    private static Cache<String, String> guavaCache = null;

    private LocalCacheManagerV7() {
        guavaCache = CacheBuilder.newBuilder().build();
    }

    public static class LocalCacheManagerHolder {
        private static final LocalCacheManagerV7 LOCAL_CACHE_MANAGER_V_7 = new LocalCacheManagerV7();
    }

    public static LocalCacheManagerV7 getInstance() {
        return LocalCacheManagerHolder.LOCAL_CACHE_MANAGER_V_7;
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
