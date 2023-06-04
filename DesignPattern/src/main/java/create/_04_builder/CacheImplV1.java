package create._04_builder;

import java.util.Map;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._04_builder
 * @author: aishuang
 * @date: 2023-04-10 05:08
 */
public class CacheImplV1<K, V> implements ICache<K, V> {

    /**
     * 初始化容量，必须
     */
    private final int initialCapacity;

    /**
     * 最大数量，必须
     */
    private final long maximumSize;

    private final Map<String, String> cacheMap = null;

    /**
     * 并行等级，决定segment数量的参数
     */
    private int concurrencyLevel = -1;

    /**
     * 最大权重
     */
    private long maximumWeight = -1L;

    public CacheImplV1(int initialCapacity, long maximumSize, int concurrencyLevel, long maximumWeight) {
        this.initialCapacity = initialCapacity;
        this.maximumSize = maximumSize;
        this.concurrencyLevel = concurrencyLevel;
        this.maximumWeight = maximumWeight;
    }

    @Override
    public String toString() {
        return "xxx";
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public void get(K key) {

    }
}
