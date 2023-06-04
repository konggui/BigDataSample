package ml.features;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import org.apache.spark.ml.feature.Bucketizer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

/*
类别型值 数值化
连续数值 离散化
数值型  归一化

分箱操作 ： 连续数值 离散化
 */
public class BuckrizerTest {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().master("local[*]").getOrCreate();
        List<Row> list = new ArrayList<>();
        list.add(RowFactory.create(-10.0));
        list.add(RowFactory.create(-5.0));
        list.add(RowFactory.create(-3.0));
        list.add(RowFactory.create(-0.3));
        list.add(RowFactory.create(0.0));
        list.add(RowFactory.create(0.2));
        list.add(RowFactory.create(4.0));
        list.add(RowFactory.create(5.0));
        list.add(RowFactory.create(6.0));
        List<StructField> fields = new ArrayList<>();
        fields.add(DataTypes.createStructField("features", DataTypes.DoubleType, false));
        StructType schema = DataTypes.createStructType(fields);
        // 数据分箱规则 左闭右开
        double[] splits = {Double.NEGATIVE_INFINITY, -2, 0, 2, Double.POSITIVE_INFINITY};
        Dataset<Row> ds = spark.createDataFrame(list, schema);

        Bucketizer bucketizer = new Bucketizer().setInputCol("features").setOutputCol("buckrierFeatures")
            .setSplits(splits);
        Dataset<Row> ds2 = bucketizer.transform(ds);
        ds2.printSchema();
        ds2.show(false);

        spark.close();
    }
}
