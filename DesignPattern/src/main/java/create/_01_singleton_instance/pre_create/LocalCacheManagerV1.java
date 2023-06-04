package create._01_singleton_instance.pre_create;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;


/**
 * @desc: 常量方式
 * @program: MapPOI
 * @packagename: create.singleton_instance.pre_create
 * @author: aishuang
 * @date: 2023-04-10 03:37
 */
public class LocalCacheManagerV1 implements ILocalCache{
    public static final Logger LOG = LoggerFactory.getLogger(LocalCacheManagerV1.class);
    public static final LocalCacheManagerV1 LOCAL_CACHE_MANAGER_V_1 = new LocalCacheManagerV1();
    private final Cache<String, String> guavaCache;

    private LocalCacheManagerV1() {
        LOG.debug("提前实例化：第一版代码（属性初始化）");
        LOG.debug("初始化缓存");
        guavaCache = CacheBuilder.newBuilder().build();

    }

    public static LocalCacheManagerV1 getInstance() {
        return LOCAL_CACHE_MANAGER_V_1;
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
