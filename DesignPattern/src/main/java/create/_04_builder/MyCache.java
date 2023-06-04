package create._04_builder;

import lombok.ToString;

import java.util.Map;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._04_builder
 * @author: aishuang
 * @date: 2023-04-10 05:30
 */
@ToString
public class MyCache<K, V> {
    private final Map<K, V> cacheMap = null;
    /**
     * 初始化容量，必须
     */
    private final int initialCapacity;

    /**
     * 最大数量，必须
     */
    private final long maximumSize;


    /**
     * 并行等级，决定segment数量的参数
     */
    private int concurrencyLevel = -1;

    /**
     * 最大权重
     */
    private long maximumWeight = -1L;

    private MyCache(MyCacheBuilder myCacheBuilder) {
        this.initialCapacity = myCacheBuilder.initialCapacity;
        this.maximumSize = myCacheBuilder.maximumSize;
        this.concurrencyLevel = myCacheBuilder.concurrencyLevel;
        this.maximumWeight = myCacheBuilder.maximumWeight;
    }

    public static class MyCacheBuilder<K, V> {
        private final Map<String, String> cacheMap = null;
        /**
         * 初始化容量，必须
         */
        private final int initialCapacity;

        /**
         * 最大数量，必须
         */
        private final long maximumSize;


        /**
         * 并行等级，决定segment数量的参数
         */
        private int concurrencyLevel = -1;

        /**
         * 最大权重
         */
        private long maximumWeight = -1L;

        public MyCacheBuilder(int initialCapacity, long maximumSize) {
            this.initialCapacity = initialCapacity;
            this.maximumSize = maximumSize;
        }

        public MyCacheBuilder setConcurrencyLevel(int concurrencyLevel){
            this.concurrencyLevel = concurrencyLevel;
            return this;
        }

        public MyCacheBuilder setMaximumWeight(int maximumWeight) {
            this.maximumWeight = maximumWeight;
            return this;
        }

        public MyCache build() {
            return new MyCache<K, V>(this);
        }
    }


    public void put(K key, V value) {

    }

    public void get(K key) {

    }
}
