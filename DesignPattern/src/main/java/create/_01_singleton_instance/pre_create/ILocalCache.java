package create._01_singleton_instance.pre_create;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create.singleton_instance
 * @author: aishuang
 * @date: 2023-04-10 03:34
 */
public interface ILocalCache {
    void put(String key, String value);

    String get(String key);
}
