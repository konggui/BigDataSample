package pers.aishuang.spark.demo.sink;


import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.IOException;

/**
 * @Desc: 数据分组（分文件夹）写入HDFS
 *
 * @Author: AiShuang
 * @Date: 2022/4/1 0:24
 */
public class GroupBySink2HDFS {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .master("local[*]")
                .appName("adc")
                .getOrCreate();
        String inputPath = "F:\\workspace\\JavaFlink\\spark\\src\\main\\resources\\1.txt";
        String prefixOutputPath = "F:\\workspace\\JavaFlink\\spark\\src\\main\\resources\\output";
        RDD<String> rdd = spark.sparkContext().textFile(inputPath,3);
        rdd.toJavaRDD()
                .mapToPair(value -> new Tuple2<>(value.split(" ")[0],value))
                .saveAsHadoopFile(
                        prefixOutputPath,
                        String.class,
                        String.class,
                        MultipleOutputWriter.class
                );
    }

    private static class MultipleOutputWriter extends MultipleTextOutputFormat<String, String> {
        @Override
        protected String generateFileNameForKeyValue(String key, String value, String name) {
            if (StringUtils.isEmpty(key)) {
                return "empty/" + name;
            } else {
                return key + "/" + name;
            }
        }

        @Override
        protected String generateActualKey(String key, String value) {
            return null;
        }
    }
}
