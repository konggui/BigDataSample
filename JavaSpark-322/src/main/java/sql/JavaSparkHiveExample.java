/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sql;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JavaSparkHiveExample {

    public static class Record implements Serializable {
        private int key;
        private String value;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        String warehouseLocation = new File("spark-warehouse").getAbsolutePath();
        SparkSession spark = SparkSession
            .builder()
            .master("local[*]")
            .appName("Java Spark Hive Example")
            .config("spark.sql.warehouse.dir", "hdfs://localhost:9000/user/hive/warehouse/")
            .enableHiveSupport()
            .getOrCreate();
        spark.sparkContext().setLogLevel("ERROR");

        spark.sql("CREATE TABLE src \n" +
                "(key INT, value STRING)");
        spark.sql("with t1 as (\n" +
                "select 1 key, 'aishuang' value union all\n" +
                "select 2 key, 'zhangsan' value union all\n" +
                "select 3 key, 'wangwu' value union all\n" +
                "select 4 key, 'zhaoliu' value\n" +
                ")\n" +
                "INSERT OVERWRITE TABLE src select * from t1");
        spark.sql("show tables").show();
        spark.sql("drop table if exists src");
        spark.sql("show databases").show();
        spark.sql("use mappoi");
        spark.sql("show databases").show();
        spark.sql("show tables").show();
        spark.sql("drop table if exists src");
        spark.sql("CREATE TABLE src \n" +
                "(key INT, value STRING)\n" +
                "STORED AS ORC\n" +
                "LOCATION 'hdfs://localhost:9000/user/hive/warehouse/mappoi.db/src'\n" +
                "tblproperties(\"orc.compress\"=\"snappy\")");
        spark.sql("with t1 as (\n" +
                "select 1 key, 'aishuang' value union all\n" +
                "select 2 key, 'zhangsan' value union all\n" +
                "select 3 key, 'wangwu' value union all\n" +
                "select 4 key, 'zhaoliu' value\n" +
                ")\n" +
                "INSERT OVERWRITE TABLE src select * from t1");
        spark.sql("SELECT * FROM src").show();
        spark.sql("SELECT COUNT(*) FROM src").show();
        Dataset<Row> sqlDF = spark.sql("SELECT key, value FROM src WHERE key < 10 ORDER BY key");
        Dataset<String> stringsDS = sqlDF.map(
                (MapFunction<Row, String>) row -> "Key: " + row.get(0) + ", Value: " + row.get(1),
                Encoders.STRING());
        stringsDS.show();
        List<Record> records = new ArrayList<>();
        for (int key = 1; key < 100; key++) {
            Record record = new Record();
            record.setKey(key);
            record.setValue("val_" + key);
            records.add(record);
        }
        Dataset<Row> recordsDF = spark.createDataFrame(records, Record.class);
        recordsDF.createOrReplaceTempView("records");
        spark.sql("SELECT * FROM records r JOIN src s ON r.key = s.key").show();
        spark.stop();
    }
}
