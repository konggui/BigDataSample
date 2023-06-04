package example;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example
 * @author: aishuang
 * @date: 2023-04-14 02:54
 */
public class HelloFlink {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Integer> source = env.fromCollection(Arrays.asList(1, 2, 3));
        source.map(x -> x + 1).print();
        env.execute("HelloFlink");
    }
}
