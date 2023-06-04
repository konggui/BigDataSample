package ml.test;

import org.apache.spark.ml.feature.LabeledPoint;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: ml.test
 * @author: aishuang
 * @date: 2023-05-11 03:14
 */
public class LabeledPointTest {
    // LabeledPoint是建立向量标签的静态类，主要有两个方法：
    // 1> Features用于显示打印标记点所代表的数据内容，而label用于显示标记数。

    public static void main(String[] args) {
        Vector v1 = Vectors.dense(2, 0, 6);
        LabeledPoint p = new LabeledPoint(1, v1);
        System.out.println(p.features());
        System.out.println(p.label());

        int[] arr1 = {0,1,2,3};
        double[] arr2 = {9,5,2,7};
        Vector v2 = Vectors.sparse(4, arr1, arr2);
        LabeledPoint p2 = new LabeledPoint(2, v2);
        System.out.println(p2.features());
        System.out.println(p2.label());
    }
}
