package ml.test;

import org.apache.spark.ml.linalg.Matrices;
import org.apache.spark.ml.linalg.Matrix;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: ml.test
 * @author: aishuang
 * @date: 2023-05-11 03:40
 */
public class LocalMatrixTest {
    // LocalMatrix本地矩阵
    // 局部矩阵具有整数类型的行和列索引和双类型值，存储在单个机器上。
    // MLlib支持密集矩阵，其入口值以列主序列存储在单个双阵列中，稀疏矩阵的
    // 非零入口值以列主要顺序存储在压缩稀疏列（CSC）格式中。
    // 局部矩阵的基类是Matrix,提供了两个实现： DenseMatrix和SparseMatrix
    // 建议使用Matrices中实现的工厂方法来创建本地矩阵
    public static void main(String[] args) {
        // matrix((1.0,2.0), (3.0,4.0), (5.0,6.0))
        double[] arr1 = {1.0, 3.0, 5.0, 2.0, 4.0, 6.0};
        Matrix dense = Matrices.dense(3, 2, arr1);
        System.out.println(dense.apply(1, 0));

        int[] arr2 = {0, 1, 3};
        int[] arr3 = {0, 2, 1};
        double[] arr4 = {9, 6, 8};
        // matrix((9.0, 0.0), (0.0, 8.0), (0.0, 6.0))
        Matrix sparse = Matrices.sparse(3, 2, arr2, arr3, arr4);
        System.out.println(sparse.apply(3, 1));
    }
}
