package example.streaming.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @desc:
 * @program: BigDataSample
 * @packagename: example.streaming.test
 * @author: aishuang
 * @date: 2023-06-04 13:17
 */
public class WindowTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        DataStreamSource<Order> orderSource = env.addSource(new SourceFunction<Order>() {
            private boolean flag = true;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            @Override
            public void run(SourceContext<Order> ctx) throws Exception {
                Random random = new Random();
                while (flag) {
                    String orderId = UUID.randomUUID().toString();
                    int userId = random.nextInt(2);
                    int money = random.nextInt(101);
                    // 随机模拟延迟
                    long eventTime = System.currentTimeMillis() - random.nextInt(5) * 1000;
                    String time = sdf.format(new Date(eventTime));
                    ctx.collect(new Order(orderId, userId, money, eventTime, time));
                    Thread.sleep(Duration.ofSeconds(1).toMillis());
                }
            }

            @Override
            public void cancel() {
                flag = false;
            }
        });

        orderSource.printToErr();

        // 在新版本中默认就是EventTime
        // env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        SingleOutputStreamOperator<Order> orderWithWaterMark = orderSource.assignTimestampsAndWatermarks(
            WatermarkStrategy.<Order>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                .withTimestampAssigner((order, timestamp) -> order.getEventTime())
        );

        // Flink-1.12
        SingleOutputStreamOperator<Order> result = orderWithWaterMark.keyBy(Order::getUserId)
            .window(TumblingEventTimeWindows.of(Time.seconds(5)))
            .sum("money");
        result.print();
        env.execute("WindowTestTask");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order{
        private String orderId;
        private Integer userId;
        private Integer money;
        private Long eventTime;
        private String time;
    }
}
