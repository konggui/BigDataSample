package example.batch.base;

import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.batch.base
 * @author: aishuang
 * @date: 2023-04-17 06:21
 */
public class MinVsMinByDemoTask {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Tuple3<Integer, Integer, Integer>> source = env.fromElements(
            Tuple3.of(1, 3, 2),
            Tuple3.of(1, 1, 2),
            Tuple3.of(1, 2, 3),
            Tuple3.of(1, 111, 1),
            Tuple3.of(1, 1, 1),
            Tuple3.of(1, 2, 0),
            Tuple3.of(1, 33, 2)
        );
        // 保留第一次其它字段的值，比对的值随比对结果改变
        source.keyBy(t -> t.f0).min(2).print("min>>>");
        // 将第一次出现的最小值那一行结果一直保留打印
        source.keyBy(t -> t.f0).minBy(2).printToErr("minBy>>>");

        env.execute();
    }
}
