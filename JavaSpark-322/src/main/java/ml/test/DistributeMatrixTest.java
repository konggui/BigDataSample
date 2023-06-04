package ml.test;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.linalg.distributed.*;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple3;

import java.util.Arrays;


/**
 * Spark的DistributedMatri 分布式矩阵详解及实战
 * 分布式矩阵由长整型行列索引 和 双精度浮点型值 数据组成，分布式存储在一个或多个RDD中，
 * 对于巨大的分布式矩阵来说，选择正确的存储格式非常重要，将一个分布式矩阵转化为另一个不同格式需要shuffle，代价很高。
 * 在MLlib实现了三类分布式矩阵存储格式，分别是行矩阵（RowMatrix）、行索引矩阵（IndexedRowMatrix)、三元组矩阵（CoordinateMatrix）和分块矩阵(BlockMatrix)等四种
 */
public class DistributeMatrixTest {
    public static void main(String[] args) {
        // 行矩阵RowMatrix
        // 行矩阵是一个面向行的分布式矩阵，行索引是没有具体含义的。比如一系列特征向量的一个集合，通过一个RDD来代表所有的行；
        // 每一个行就是一个本地向量
        SparkSession spark = SparkSession.builder().master("local[*]").appName("test").getOrCreate();
        SparkContext sc = spark.sparkContext();
        RDD<String> rdd = sc.textFile("JavaSpark-322/sample_data/input2/RowMatrix.txt", 3);
        JavaRDD<Vector> mapRdd = rdd.toJavaRDD()
            .map(v -> Arrays.asList(v.split(" ")).stream().mapToDouble(s -> Double.valueOf(s).doubleValue()).toArray())
            .map(v -> Vectors.dense(v));
        // 读入行矩阵， RowMatrix是一个transform操作，得到的并不是最终的运行结构。
        RowMatrix rm = new RowMatrix(mapRdd.rdd());
        System.out.println(rm.numRows()); // 打印行数
        System.out.println(rm.numCols()); // 打印列数

        // 行索引矩阵 IndexedRowMatrix
        // 它由索引行的RDD支持，因此每行由其索引（long-typed) 和本地向量表示
        // 一个IndexedRowMatrix可以从创建RDD[IndexedRow]，例如，在IndexedRow已经结束的包装(long,Vector).
        // 一个IndexedRowMatrix可以被转换为RowMatrix通过降低其行索引。
        JavaRDD<IndexedRow> mapRdd2 = mapRdd.map(vd -> new IndexedRow(vd.size(), vd));
        IndexedRowMatrix irm = new IndexedRowMatrix(mapRdd2.rdd());
        System.out.println(irm.getClass());
        irm.rows().toJavaRDD().foreach(v -> System.out.println(v));

        /*
        // 三元组矩阵 CoordinateMatrix
        // CoordinateMatrix是由其条目的RDD支持的分布式矩阵。
        // 每个条目都是一个元组(i:Long, j:Long, value:Double), 其中i是行索引，j是列索引，value是条目值。
        // 只有当矩阵的两个维度都很大并且矩阵非常稀疏时，才应该使用A CoordinateMatrix
        JavaRDD<MatrixEntry> map = sc.textFile("", 3)
            .toJavaRDD()
            .map(v -> Arrays.asList(v.split(" ")).stream().mapToDouble(s -> Double.valueOf(s).doubleValue()).toArray())
            .map(v -> new Tuple3(v[0], v[1], v[3]))
            .map(t -> new MatrixEntry(Long.parseLong(t._1().toString()), Long.parseLong(t._2().toString()), Double.valueOf(t._3().toString())));

        CoordinateMatrix c = new CoordinateMatrix(map.rdd());
        System.out.println(c.numRows());
        System.out.println(c.numCols());
        System.out.println(c.entries());

        // 分块矩阵 BlockMatrix
        // BlockMatrix是支持矩阵分块RDD的分布式矩阵，其中矩阵分块由((int,int),matrix)元组所构成
        // (int, int)是该部分矩阵所处的矩阵的索引位置， Matrix表示该索引位置上的子矩阵
        // 分块矩阵支持矩阵加法和乘法，并设有辅助函数验证用于检查矩阵是否设置正确。
        BlockMatrix cache = c.toBlockMatrix().cache();
        cache.validate();
        // A^T A
        BlockMatrix multiply = cache.transpose().multiply(cache);
        // 因为transpose是transform操作不会直接得到结果，调用action算法会得到结果。
        multiply.persist(StorageLevel.DISK_ONLY());
        spark.close();
*/
    }
}
