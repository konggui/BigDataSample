package create._04_builder;

/**
 * @desc:
 *  volatile transient
 * @program: MapPOI
 * @packagename: create._04_builder
 * @author: aishuang
 * @date: 2023-04-10 05:44
 */
public class TestMyCache {
    public static void main(String[] args) {
        TestMyCache test = new TestMyCache();
        test.demo01();
        StringBuilder sb = new StringBuilder("A");
        sb.append("a").append("b");
        System.out.println(sb);
    }

    public void demo01() {
        MyCache<String, String> myCache = new MyCache.MyCacheBuilder(10, 5)
                .setConcurrencyLevel(20)
                .setMaximumWeight(30)
                .build();
        System.out.println(myCache);
        System.out.println();
    }
}
