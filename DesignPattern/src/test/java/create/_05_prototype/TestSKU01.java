package create._05_prototype;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: create._05_prototype
 * @author: aishuang
 * @date: 2023-04-10 06:56
 */
public class TestSKU01 {
    public static void main(String[] args) {
        TestSKU01 test = new TestSKU01();
        test.demo01();
    }

    public void demo01() {
        SKU01 sku01 = new SKU01("001", "name", 10);
        SKU01 sku02 = null;
        try {
            sku02 = (SKU01) sku01.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(sku01);
        System.out.println(sku02);
        sku01.setId("002");
        System.out.println(sku01);
        System.out.println(sku02);
    }
}
