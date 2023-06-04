package ml.example;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
SparkMLlib的 SummaryStatistic摘要统计
 */
public class SummaryStatisticTest {
    // 均值和方差
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().master("local[*]").appName("test").getOrCreate();
        SparkContext sc = spark.sparkContext();
        JavaRDD<Vector> map = sc.textFile("JavaSpark-322/sample_data/input/b.txt", 3)
            .toJavaRDD()
            .map(v -> Arrays.asList(v.split(" ")).stream().mapToDouble(s -> Double.valueOf(s).doubleValue()).toArray())
            .map(v -> Vectors.dense(v));
        // 获取statistics实例
        MultivariateStatisticalSummary summary = Statistics.colStats(map.rdd());
        // 计算均值
        System.out.println(summary.mean());
        // 计算标准差
        System.out.println(summary.variance());

        List<Vector> list = new ArrayList<>();
        list.add(Vectors.dense(1.0, 10.0, 100.0));
        list.add(Vectors.dense(2.0, 20.0, 200.0));
        list.add(Vectors.dense(3.0, 30.0, 300.0));
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(sc);
        JavaRDD<Vector> parallelize = jsc.parallelize(list);
        MultivariateStatisticalSummary summary2 = Statistics.colStats(parallelize.rdd());
        System.out.println(summary2.mean());
        System.out.println(summary2.variance());
        System.out.println(summary2.numNonzeros());
    }
}
