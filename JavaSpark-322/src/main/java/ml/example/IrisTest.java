package ml.example;

import org.apache.spark.SparkContext;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.DecisionTreeClassifier;
import org.apache.spark.ml.feature.*;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/*
source -> 特征组合 -> 最大值最小值归一化 -> 定性描述数值化 -> 算法
Breeze Matlab Numpy

抽取 -> 特征工程 -> 准备算法 -> 模型训练 ->
 */
public class IrisTest {
    // Estimator估量器（学习器）
    // 如果一个操作继承了Estimator的类，需要先fit再执行transform方法完成
    // 当一个操作继承了Transformer的类，直接执行transform方法完成
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().master("local[*]").getOrCreate();
        // todo-1: 读取Iris鸢尾花数据集
        String filePath = "E:\\workspace\\MapPOI\\JavaSpark-322\\sample_data\\input3\\iris.csv";
        Dataset<Row> ds = spark.read().format("csv")
            .option("header", true)
            .option("inferSchema", true)
            .option("sep", ",")
            .load(filePath);
        // todo-2: 查看数据集的基本信息
        ds.printSchema();
        ds.show(10, false);
        // todo-3: 统计分析
        Dataset<Row> max = ds.groupBy("class")
            .max("sepal_length");
        max.show(false);
        // todo-4: 特征工程
        //     -- 4-1 特征属性组合
        VectorAssembler assembler = new VectorAssembler()
            .setInputCols(new String[]{"sepal_length", "sepal_width", "petal_length", "petal_width"})
            .setOutputCol("features");
        Dataset<Row> ds2 = assembler.transform(ds);
        ds2.printSchema();
        ds2.show(false);
        //     -- 4-2 处理四个特征列转为MinMaxScaler 归一化
        MinMaxScaler minMaxScaler = new MinMaxScaler().setInputCol("features").setOutputCol("minmaxFeatures");
        MinMaxScalerModel scalerModel = minMaxScaler.fit(ds2);
        Dataset<Row> ds3 = scalerModel.transform(ds2);
        ds3.printSchema();
        ds3.show(false);
        //     -- 4-3 将类别标签转化为数值型，描述类型数值化
        StringIndexer stringIndexer = new StringIndexer().setInputCol("class").setOutputCol("classLabel");
        StringIndexerModel stringIndexerModel = stringIndexer.fit(ds3);
        Dataset<Row> ds4 = stringIndexerModel.transform(ds3);
        ds4.printSchema();
        ds4.show(false);

        // todo-5: 准备算法--监督学习分类算法
        DecisionTreeClassifier decisionTreeClassifier = new DecisionTreeClassifier()
            .setFeaturesCol("minmaxFeatures")
            .setLabelCol("classLabel");

        // *** pipeline管道 ***
        Dataset<Row>[] dsArray = ds.randomSplit(new double[]{0.8, 0.2}, 123L);
        Dataset<Row> traningDS = dsArray[0];
        Dataset<Row> testDS = dsArray[1];
        Pipeline pipeline = new Pipeline()
            .setStages(new PipelineStage[]{assembler, minMaxScaler, stringIndexer, decisionTreeClassifier});

        // todo-6: 训练模型： 模型 = 算法(决策树) + 数据（特征工程处理后的数据)
        PipelineModel pipelineModel = pipeline.fit(traningDS);
        Dataset<Row> tDs = pipelineModel.transform(traningDS);
        tDs.show(false);
        tDs.select("classLabel", "minmaxFeatures").show(false);
        Dataset<Row> testDs = pipelineModel.transform(testDS);
        testDs.show(false);
        testDs.select("classLabel", "minmaxFeatures").show(false);
        // todo-7: 模型校验 -- 是否发生过拟合
        // todo-8: 模型保存
        // todo-9: 新数据的预测

        spark.close();
    }
}
