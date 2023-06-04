package ml.features;

import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.linalg.Vector;
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
 * @date: 2023-05-12 19:50
 */
public class VectorAssembleTest {
    // Assemble： 组装
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().master("local[*]").getOrCreate();
        List<Row> list = new ArrayList<>();
        list.add(RowFactory.create(0, 18, 1.0, Vectors.dense(0.0, 10.0, 0.5), 1.0));
        List<StructField> fields = new ArrayList<>();
        fields.add(DataTypes.createStructField("id", DataTypes.IntegerType, false));
        fields.add(DataTypes.createStructField("hour", DataTypes.IntegerType, false));
        fields.add(DataTypes.createStructField("mobile", DataTypes.DoubleType, false));
        fields.add(DataTypes.createStructField("userFeatures", new VectorUDT(), false));
        fields.add(DataTypes.createStructField("clicked", DataTypes.DoubleType, false));
        StructType schema = DataTypes.createStructType(fields);
        Dataset<Row> ds = spark.createDataFrame(list, schema);
        VectorAssembler assembler = new VectorAssembler()
            .setInputCols(new String[]{"hour", "mobile", "userFeatures"})
            .setOutputCol("features");
        ds.show();
        ds.printSchema();
        Dataset<Row> ds2 = assembler.transform(ds);
        ds2.printSchema();
        ds2.show(false);
        spark.close();
    }
}
