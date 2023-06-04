package example.rdd;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;
import utils.FileUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.rdd
 * @author: aishuang
 * @date: 2023-04-13 22:49
 */

public class RddExampleTask {
    private static String inputPath = "JavaSpark-322/sample_data/input/*.txt";
    private static String outputPath = "JavaSpark-322/sample_data/output/data1";

    public static void main(String[] args) throws IOException {
        SparkConf sparkConf = new SparkConf().setAppName("AA").setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);

        //
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> rdd1 = jsc.parallelize(data);
        int reduce = rdd1.reduce((a, b) -> a + b);
        rdd1.persist(StorageLevel.DISK_ONLY());
        System.out.println(reduce);

        //可以正则匹配
        JavaRDD<String> rdd2 = jsc.textFile(inputPath);
        JavaPairRDD<String, Integer> javaPairRDD = rdd2.flatMap(line -> {
            return StringUtils.isBlank(line) ? Collections.EMPTY_LIST.iterator()
                : Arrays.stream(line.split(" ")).map(StringUtils::trim).filter(StringUtils::isNotBlank).collect(Collectors.toList()).iterator();
        }).mapToPair(s -> new Tuple2(s, 1));
        JavaPairRDD<String, Integer> counts = javaPairRDD.reduceByKey((a, b) -> a + b);
        if (FileUtil.isExistsLocalFilePath(outputPath)) {
            System.out.println("删除");
            FileUtil.removeLocalFilePath(outputPath);
        }
        counts.saveAsTextFile(outputPath);
    }


}
