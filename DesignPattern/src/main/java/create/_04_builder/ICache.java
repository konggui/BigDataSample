package create._04_builder;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._04_builder
 * @author: aishuang
 * @date: 2023-04-10 05:07
 */
public interface ICache<K, V> {
    void put(K key , V value);
    void get(K key);
}
