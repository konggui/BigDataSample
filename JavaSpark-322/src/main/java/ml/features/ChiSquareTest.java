package ml.features;


import org.apache.spark.ml.feature.ChiSqSelector;
import org.apache.spark.ml.feature.ChiSqSelectorModel;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
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
 * @date: 2023-05-12 21:13
 */
public class ChiSquareTest {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().master("local[*]").getOrCreate();
        List<Row> list = new ArrayList<>();
        list.add(RowFactory.create(7, Vectors.dense(0.0, 0.0, 18.0, 1.0), 1.0));
        list.add(RowFactory.create(8, Vectors.dense(0.0, 1.0, 12.0, 0.0), 0.0));
        list.add(RowFactory.create(9, Vectors.dense(1.0, 0.0, 15.0, 0.1), 0.0));
        List<StructField> fields = new ArrayList<>();
        fields.add(DataTypes.createStructField("id", DataTypes.IntegerType, false));
        fields.add(DataTypes.createStructField("features", new VectorUDT(), false));
        fields.add(DataTypes.createStructField("clicked", DataTypes.DoubleType, false));
        StructType schema = DataTypes.createStructType(fields);
        Dataset<Row> ds = spark.createDataFrame(list, schema);
        ChiSqSelector chiSqSelector = new ChiSqSelector()
            .setFeaturesCol("features")
            .setLabelCol("clicked")
            .setNumTopFeatures(2)
            .setOutputCol("chiSqSelector");
        // 这里的卡方验证需要使用pearson相似度-- 类似余弦相似度--
        ChiSqSelectorModel model = chiSqSelector.fit(ds);
        Dataset<Row> ds2 = model.transform(ds);
        ds2.printSchema();
        ds2.show(false);
        spark.close();
    }
}
