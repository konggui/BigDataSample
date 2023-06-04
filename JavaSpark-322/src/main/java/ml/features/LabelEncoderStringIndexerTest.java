package ml.features;

import org.apache.spark.ml.feature.*;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: ml.features
 * @author: aishuang
 * @date: 2023-05-12 20:19
 */
public class LabelEncoderStringIndexerTest {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().master("local[*]").appName("test").getOrCreate();
        List<Row> list = new ArrayList<>();
        list.add(RowFactory.create(0, "a"));
        list.add(RowFactory.create(1, "b"));
        list.add(RowFactory.create(2, "c"));
        list.add(RowFactory.create(3, "a"));
        list.add(RowFactory.create(4, "a"));
        list.add(RowFactory.create(5, "c"));
        List<String> list2 = new ArrayList<>();
        list2.add("A");
        list2.add("B");
        list2.add("C");
        List<StructField> fields = new ArrayList<>();
        fields.add(DataTypes.createStructField("id", DataTypes.IntegerType, true));
        fields.add(DataTypes.createStructField("category", DataTypes.StringType, true));
        Dataset<Row> df = spark.createDataFrame(list, DataTypes.createStructType(fields));
        df.printSchema();
        df.show();

        // 特征工程，将类别型数据转化为数值型
        // .setInputCol() 输入的特征来源数据集本省
        // .setOutputCol() 输出是由用户可以自定义 “indexerNum”
        StringIndexer indexer = new StringIndexer().setInputCol("category").setOutputCol("indexerNum");
        // 上述代码已经准备好了indexer的操作，但是由于spark是惰性求值需要获取触发操作
        StringIndexerModel indexerModel = indexer.fit(df);
        // 上述代码训练得到模型
        Dataset<Row> indexResultDS = indexerModel.transform(df);
        indexResultDS.printSchema();
        indexResultDS.show();
        // 什么会由index转化为原来的数据---需要在监督学习的分类任务中经常预测 值和原来的值的映射
        // .setLabels(indexerModel.labels) 这里值的是获取映射关系
        IndexToString indexToString = new IndexToString().setInputCol("indexerNum").setOutputCol("beforeCategory")
            .setLabels(indexerModel.labels());
        Dataset<Row> categoryDF = indexToString.transform(indexResultDS);
        categoryDF.printSchema();
        categoryDF.show();

        // 没有顺序的情况下请使用读 热编码
        // 这里要求读 热编码的输入必须是经过stringindexer之后的结果，这里就是indexerNum
        OneHotEncoder oneHotEncoder = new OneHotEncoder().setInputCol("indexerNum")
            .setOutputCol("oneHotIndexer").setDropLast(false);
        OneHotEncoderModel fit = oneHotEncoder.fit(indexResultDS);
        Dataset<Row> transform = fit.transform(indexResultDS);
        transform.printSchema();
        transform.show();

        /*
        +---+--------+----------+-------------+
        | id|category|indexerNum|oneHotIndexer|
        +---+--------+----------+-------------+
        |  0|       a|       0.0|(3,[0],[1.0])|
        |  1|       b|       2.0|(3,[2],[1.0])|
        |  2|       c|       1.0|(3,[1],[1.0])|
        |  3|       a|       0.0|(3,[0],[1.0])|
        |  4|       a|       0.0|(3,[0],[1.0])|
        |  5|       c|       1.0|(3,[1],[1.0])|
        +---+--------+----------+-------------+
         */
        spark.close();
    }
}
