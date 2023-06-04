package ml.test;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: ml.test
 * @author: aishuang
 * @date: 2023-05-11 19:01
 */
public class CorrelationTest {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().master("").appName("").getOrCreate();
        JavaSparkContext jsc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        List<Integer> list = new ArrayList<>();
        JavaRDD<Integer> rdd = jsc.parallelize(list);
    }
}
