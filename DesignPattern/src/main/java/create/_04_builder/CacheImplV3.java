package create._04_builder;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._04_builder
 * @author: aishuang
 * @date: 2023-04-10 05:08
 */
@Setter
@Getter
public class CacheImplV3<K, V> implements ICache<K, V> {

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

    public CacheImplV3(int initialCapacity, long maximumSize) {
        this.initialCapacity = initialCapacity;
        this.maximumSize = maximumSize;
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
