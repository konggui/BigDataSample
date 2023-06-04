package create._01_singleton_instance.pre_create;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create.singleton_instance.pre_create
 * @author: aishuang
 * @date: 2023-04-10 04:14
 */
public class TestLocalCacheManagerV2 {
    public static final Logger LOG = LoggerFactory.getLogger(TestLocalCacheManagerV1.class);

    public static void main(String[] args) {
        TestLocalCacheManagerV2 obj = new TestLocalCacheManagerV2();
        obj.demo01();
    }

    public void demo01() {
        LocalCacheManagerV2 instance1 = LocalCacheManagerV2.getInstance();
        LocalCacheManagerV2 instance2 = LocalCacheManagerV2.getInstance();
        LOG.error("instance1 = instance2 , is "+  (instance1 == instance2));
        instance1.put("name", "java");
        System.out.println(instance1.get("name"));
        System.out.println(instance2.get("name"));
        instance2.put("name", "golang");
        System.out.println(instance1.get("name"));
        System.out.println(instance2.get("name"));
    }
}
