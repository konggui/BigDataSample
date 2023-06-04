package create._01_singleton_instance.use_create;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import create._01_singleton_instance.pre_create.TestLocalCacheManagerV1;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create.singleton_instance.use_create
 * @author: aishuang
 * @date: 2023-04-10 04:21
 */
public class TestLocalCacheManagerV3 {
    public static final Logger LOG = LoggerFactory.getLogger(TestLocalCacheManagerV3.class);

    public static void main(String[] args) {
        TestLocalCacheManagerV1 obj = new TestLocalCacheManagerV1();
        obj.demo01();
    }

    public void demo01() {
        LocalCacheManagerV3 instance1 = LocalCacheManagerV3.getInstance();
        LocalCacheManagerV3 instance2 = LocalCacheManagerV3.getInstance();
        LOG.error("instance1 = instance2 , is "+  (instance1 == instance2));
        instance1.put("name", "java");
        System.out.println(instance1.get("name"));
        System.out.println(instance2.get("name"));
        instance2.put("name", "golang");
        System.out.println(instance1.get("name"));
        System.out.println(instance2.get("name"));
    }
}
