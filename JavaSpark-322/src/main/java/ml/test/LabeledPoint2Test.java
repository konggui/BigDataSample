package ml.test;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: ml.test
 * @author: aishuang
 * @date: 2023-05-11 03:26
 */
public class LabeledPoint2Test {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("test");
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        SparkContext sc = spark.sparkContext();
        RDD<LabeledPoint> rdd = MLUtils.loadLibSVMFile(sc, "JavaSpark-322/sample_data/input/a.txt");
        rdd.toJavaRDD().foreach(v -> System.out.println(v));
    }
}
