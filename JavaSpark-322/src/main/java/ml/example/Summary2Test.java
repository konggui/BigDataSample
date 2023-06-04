package ml.example;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;

/**
距离计算
 */
public class Summary2Test {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().master("local[*]").appName("test").getOrCreate();
        SparkContext sc = spark.sparkContext();
        JavaRDD<Vector> map = sc.textFile("", 3)
            .toJavaRDD()
            .map(v -> Arrays.asList(v.split(" ")).stream().mapToDouble(s -> Double.valueOf(s).doubleValue()).toArray())
            .map(v -> Vectors.dense(v));
        MultivariateStatisticalSummary summary = Statistics.colStats(map.rdd());
        // 计算曼哈段距离
        System.out.println(summary.normL1());
        // 计算欧几里得距离
        System.out.println(summary.normL2());
    }
}
