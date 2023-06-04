package example.sink.file;


import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;
import utils.FileUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @Desc: 数据分组（分文件夹）写入HDFS
 * @Author: AiShuang
 * @Date: 2022/4/1 0:24
 */
public class GroupBySinkTask {
    private static String inputPath = "JavaSpark-322/sample_data/input/data.txt";
    private static String prefixOutputPath = "E:/workspace/MapPOI/JavaSpark-322/sample_data/output";

    public static void main(String[] args) throws IOException {
        FileUtil.removeLocalFilePath(prefixOutputPath);
        SparkSession spark = SparkSession.builder()
            .master("local[*]")
            .appName("adc")
            .getOrCreate();

        spark.sparkContext().textFile(inputPath, 1)
            .toJavaRDD()
            .flatMap(line -> {
                return StringUtils.isBlank(line) ? Collections.EMPTY_LIST.iterator()
                    : Arrays.stream(line.split(" ")).map(StringUtils::trim).filter(StringUtils::isNotBlank).collect(Collectors.toList()).iterator();
            })
            .mapToPair(word -> new Tuple2(word, word))
           //必须是绝对路径，相对路径会报错
            .saveAsHadoopFile(prefixOutputPath, String.class, String.class, MyMultipleOutputWriter.class);
    }

    // 必须是public修饰
    public static class MyMultipleOutputWriter extends MultipleTextOutputFormat<String, String> {
        @Override
        protected String generateFileNameForKeyValue(String key, String value, String name) {
            if (StringUtils.isEmpty(key)) {
                return "empty/" + name;
            } else {
                return key + "/" + name + ".txt";
            }
        }

        @Override
        protected String generateActualKey(String key, String value) {
            return null;
        }
    }
}
