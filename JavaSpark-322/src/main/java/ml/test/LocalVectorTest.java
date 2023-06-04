package ml.test;

import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors;

/**
MLLIB支持很多机器学习算法中类型，主要有向量和矩阵两种类型
    有下面四种分类：
        1> Local vector         本地向量集，主要向Spark提供一组可进行操作的数据集合；
        2> Labeled Point        向量标签，让用户能够分类不同的数据集合；
        3> Local matrix         本地矩阵，将数据集合以矩阵形式存储在本地计算机中；
        4> Distribute matrix    分布式矩阵，将数据集以矩阵的形式存储在分布式的计算机中；
 */
public class LocalVectorTest {
    // 本地向量主要由两种类型构成：1> 稀疏型数据集spares 2> 密集型数据集dense
    // 在Mlib的数据支持格式中，目前仅支持整数与浮点型数。其他数据类型不在支持范围之内，主要也是因为MLLIB主要用于做数值计算。
    public static void main(String[] args) {
        // 建立密集向量
        Vector v = Vectors.dense(2, 0, 6);
        System.out.println(v.apply(2));

        // 稀疏向量
        int[] arr1 = {0, 1, 2, 3};
        double[] arr2 = {9, 5, 2, 7};
        // 第一个参数4代表输入数据的大小，一般要求大于等于输入的数据值，第二个参数是数据下标，第三个参数是数据值
        Vector v2 = Vectors.sparse(4, arr1, arr2);
        System.out.println(v2.apply(2));
        int[] arr3 = {0, 1, 5};
        double[] arr4 = {2, 3, 0};
        Vector v3 = Vectors.sparse(6, arr3, arr4);
        System.out.println(v3.apply(0));
    }
}
