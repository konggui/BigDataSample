package example.streaming.test;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.util.Collector;

import java.util.*;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.streaming.test
 * @author: aishuang
 * @date: 2023-06-04 11:29
 */
public class StateTest {
    public static List<Integer> data;

    static {
        data = new ArrayList<>();
        data.add(10);
        data.add(9);
        data.add(8);
        data.add(7);
    }

    public static void main(String[] args) throws Exception {
        // state();
        processingTimeWindow();
    }

    public static void state() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.fromCollection(data).keyBy(v -> v % 2)
            .process(new KeyedProcessFunction<Integer, Integer, Integer>() {
                private ValueState<Integer> sumState;

                @Override
                public void open(Configuration parameters) throws Exception {
                    super.open(parameters);
                    ValueStateDescriptor<Integer> sumDescriptor = new ValueStateDescriptor<>("Sum", Integer.class);
                    sumState = getRuntimeContext().getState(sumDescriptor);
                }

                @Override
                public void processElement(Integer value, Context ctx, Collector<Integer> out) throws Exception {
                    Integer oldSum = sumState.value();
                    int sum = oldSum == null ? 0 : oldSum;
                    sum += value;
                    sumState.update(sum);
                    out.collect(sum);
                }
            }).print().setParallelism(2);

        env.execute();
    }

    public static void processingTimeWindow() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Integer> source = env.addSource(new SourceFunction<Integer>() {
            private volatile boolean stop = false;

            @Override
            public void run(SourceContext<Integer> ctx) throws Exception {
                int i = 0;
                while (!stop && i < data.size()) {
                    ctx.collect(data.get(i++));
                    Thread.sleep(200);
                }
            }

            @Override
            public void cancel() {
                stop = true;
            }
        }).setParallelism(1);
        source.printToErr();

        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);

        source.keyBy(v -> v % 2)
            .process(new KeyedProcessFunction<Integer, Integer, Integer>() {
                private static final int WINDOW_SIZE = 200;
                private TreeMap<Long, Integer> windows;

                @Override
                public void open(Configuration parameters) throws Exception {
                    super.open(parameters);
                    windows = new TreeMap<>();
                }

                @Override
                public void processElement(Integer value, Context ctx, Collector<Integer> out) throws Exception {
                    long currentTime = ctx.timerService().currentProcessingTime();
                    long windowStart = currentTime / WINDOW_SIZE;

                    Integer sum = windows.getOrDefault(windowStart, 0);
                    windows.put(windowStart, sum + value);

                    NavigableMap<Long, Integer> oldWindows = windows.headMap(windowStart, false);
                    Iterator<Map.Entry<Long, Integer>> iterator = oldWindows.entrySet().iterator();
                    while (iterator.hasNext()) {
                        out.collect(iterator.next().getValue());
                        iterator.remove();
                    }
                }

                @Override
                public void close() throws Exception {
                    super.close();
                    //System.out.println(windows);
                }
            }).print().setParallelism(2);
        env.execute();
    }
}
