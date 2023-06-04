package example.udf;

import jodd.util.PropertiesUtil;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;
import utils.PropUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.udf
 * @author: aishuang
 * @date: 2023-05-12 23:25
 */
public class UDFTest {
    public static void main(String[] args) throws IOException {
       // Properties prop = PropUtil.loadProp(UDFTest.class.getClassLoader().getResource("myconfig.properties").toString());
        Properties prop = PropUtil.loadResourcesProp("myconfig.properties");
        SparkSession spark = SparkSession
            .builder()
            .master("local[*]")
            .appName("Java Spark SQL UDF scalar example")
            .getOrCreate();
        Dataset<Row> source = queryMysqlData(spark, prop);
        source.printSchema();
        source.show(false);
        // 支持写UDF1~UDF20,即输入参数最多可为20个
        spark.udf().register("getLength", (UDF1<String, Integer>) s -> s.length(), DataTypes.IntegerType);
        Dataset<Row> ds1 = source.withColumn("name_length", functions.callUDF("getLength", source.col("username")));
        ds1.printSchema();
        ds1.show(false);
        spark.close();

    }

    private static Dataset<Row> queryMysqlData(SparkSession spark, Properties prop) {
        // 注意这里sql语句必须括起来再设定一个别名
        return spark.read().jdbc(prop.getProperty("mysql_url"), prop.getProperty("mysql_querySql"), prop);
    }


}
