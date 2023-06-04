package example.batch.base;

import example.pojo.CustomEvent;
import example.streaming.source.CustomFakeSource;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.*;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.batch.base
 * @author: aishuang
 * @date: 2023-04-17 16:22
 */
public class WindowDemoTask {
    // 注意Flink1.12起，默认是事件时间
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<CustomEvent> source = env.addSource(new CustomFakeSource(1))
            .setParallelism(3);

        // todo: 时间窗口使用的是TimeWindow(),也可以使用window(), 如： .timeWindow(Time.Seconds(1))

        // ------ 滚动窗口
        // A 基于事件时间的滚动窗口
        WindowedStream<CustomEvent, String, TimeWindow> tumblingEventTimeWindow = source.keyBy(value -> value.getId())
            .window(TumblingEventTimeWindows.of(Time.seconds(5)));

        // B 基于处理时间的滚动窗口
        WindowedStream<CustomEvent, String, TimeWindow> tumblingProcessingTimeWindow = source.keyBy(value -> value.getId())
            .window(TumblingProcessingTimeWindows.of(Time.seconds(5)));

        // C 在小时级滚动窗口上设置15分钟的Offset偏移
        WindowedStream<CustomEvent, String, TimeWindow> tumblingOffsetWindow = source.keyBy(value -> value.getId())
            .window(TumblingEventTimeWindows.of(Time.hours(1), Time.minutes(15)));

        // ------ 滑动窗口
        WindowedStream<CustomEvent, String, TimeWindow> slidingEventTimeWindow = source.keyBy(value -> value.getId())
            // 窗口大小10s, 滑动步长5s
            .window(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)));

        WindowedStream<CustomEvent, String, TimeWindow> slidingProcessingTimeWindow = source.keyBy(value -> value.getId())
            .window(SlidingProcessingTimeWindows.of(Time.seconds(10), Time.seconds(5)));

        WindowedStream<CustomEvent, String, TimeWindow> slidingOffsetWindow = source.keyBy(value -> value.getId())
            // 窗口大小2h, 滑动步长1h, 偏移-8h
            .window(SlidingEventTimeWindows.of(Time.hours(2), Time.hours(1), Time.hours(-8)));

        // ------ 会话窗口
        WindowedStream<CustomEvent, String, TimeWindow> staticSessionWindow = source.keyBy(value -> value.getId())
            // 定长Session gap
            .window(EventTimeSessionWindows.withGap(Time.minutes(10)));

        WindowedStream<CustomEvent, String, TimeWindow> dynamicSessionWindow = source.keyBy(value -> value.getId())
            // 动态Session gap
            .window(EventTimeSessionWindows.withDynamicGap(new SessionWindowTimeGapExtractor<CustomEvent>() {
                @Override
                public long extract(CustomEvent customEvent) {
                    return 0;
                }
            }));

        WindowedStream<CustomEvent, String, TimeWindow> dynamicProcessingTimeSessionWindow = source.keyBy(value -> value.getId())
            // 动态Session gap
            .window(ProcessingTimeSessionWindows.withDynamicGap(new SessionWindowTimeGapExtractor<CustomEvent>() {
                @Override
                public long extract(CustomEvent customEvent) {
                    return 0;
                }
            }));
    }
}
