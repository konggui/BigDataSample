package ml.features;

import org.apache.spark.ml.feature.MaxAbsScaler;
import org.apache.spark.ml.feature.MaxAbsScalerModel;
import org.apache.spark.ml.feature.MinMaxScaler;
import org.apache.spark.ml.feature.MinMaxScalerModel;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.connector.read.streaming.SparkDataStream;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: ml.features
 * @author: aishuang
 * @date: 2023-05-12 20:57
 */
public class GuiYiTest {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().master("local[*]").getOrCreate();
        List<Row> list = new ArrayList<>();
        list.add(RowFactory.create(0, Vectors.dense(1.0, 0.5, -1.0)));
        list.add(RowFactory.create(1, Vectors.dense(2.0, 1.0, 1.0)));
        list.add(RowFactory.create(2, Vectors.dense(4.0, 10.0, 2.0)));
        List<StructField> fields = new ArrayList<>();
        fields.add(DataTypes.createStructField("id", DataTypes.IntegerType, false));
        fields.add(DataTypes.createStructField("features", new VectorUDT(), false));
        StructType schema = DataTypes.createStructType(fields);
        Dataset<Row> ds = spark.createDataFrame(list, schema);
        ds.printSchema();
        ds.show(false);
        // 准备归一化--- 最大值和最小值归一化 ：什么是归一化，就是在单位或量纲不统一的情况下会受到数值带来的影响
        // 默认归一化到0-1
        MinMaxScaler minMaxScaler = new MinMaxScaler().setInputCol("features").setOutputCol("MinMaxScalerResult");
        MinMaxScalerModel model = minMaxScaler.fit(ds);
        Dataset<Row> ds2 = model.transform(ds);
        ds2.show(false);

        MaxAbsScaler maxAbsScaler = new MaxAbsScaler().setInputCol("features").setOutputCol("MaxABSScalerResult");
        MaxAbsScalerModel model2 = maxAbsScaler.fit(ds);
        Dataset<Row> ds3 = model2.transform(ds2);
        ds3.show(false);

        spark.close();
    }
}
