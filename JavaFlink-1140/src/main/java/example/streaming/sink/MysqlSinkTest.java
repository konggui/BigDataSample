package example.streaming.sink;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.streaming.sink
 * @author: aishuang
 * @date: 2023-04-16 09:45
 */
public class MysqlSinkTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
//
//        DataStreamSource<String> streamSource = env.readTextFile("input/mappoi-operation-log.csv");
//        SingleOutputStreamOperator<OperationLogBean> mapSource = streamSource.flatMap(
//            (String line, Collector<OperationLogBean> out) -> {  if (OperationLogBean.isOperationLog(line)) out.collect(OperationLogBean.of(line));}
//        );
//
//        mapSource.addSink(
//            //参数1：sql
//            JdbcSink.sink("INSERT INTO clicks(user, url) VALUES (?,?) ",
//                //参数2：statement占位符
//                (statement, data) -> {
//                    statement.setString(1, data.);
//                    statement.setString(2, data.url);
//                },
//                //参数3：设置8666.ba0
//                JdbcExecutionOptions.builder()
//                    .withBatchIntervalMs(200)   //批次提交周期：每200ms
//                    .withBatchSize(1000)        //批次提交周期：每1000条
//                    .withMaxRetries(5)          //批次提交周期，每5次
//                    .build(),
//                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
//                    .withUrl("jdbc:mysql://hadoop102:3306/test")    //mysql地址
//                    .withDriverName("com.mysql.jdbc.Driver")
//                    .withUsername("root")
//                    .withPassword("000000")
//                    .build()
//            )
//        );

        env.execute();
    }
}
