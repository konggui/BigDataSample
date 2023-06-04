package create._05_prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._05_prototype
 * @author: aishuang
 * @date: 2023-04-10 07:03
 */
public class TestSKU02 {
    public static void main(String[] args) {
        TestSKU02 test = new TestSKU02();
        test.demo01();
    }

    public void demo01() {
        List<String> list1 = new ArrayList<String>();
        list1.add("AA");
        SKU02 sku01 = new SKU02("001", "name", 10, list1);
        SKU02 sku02 = null;
        try {
            sku02 = (SKU02) sku01.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(sku01);
        System.out.println(sku02);
        sku01.getImageList().add("BB");
        System.out.println(sku01);
        System.out.println(sku02);
    }
}
