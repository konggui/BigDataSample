package com.aishuang.bigdata.mappoi.commontool.flink113.demo;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.Collection;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: com.aishuang.bigdata.mappoi.commontool.flink113.demo
 * @author: aishuang
 * @date: 2022-11-09 22:44
 */
public class BoundedStreamWordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.readTextFile(WindowFilePathConstant.WORDS_TXT)
                .flatMap((String line, Collector<String> out) -> {
                    Arrays.stream(line.split(" ")).forEach(out::collect);
                })
                .returns(Types.STRING)
                .map(word -> Tuple2.of(word, 1L))
                .returns(Types.TUPLE(Types.STRING, Types.LONG))
                .keyBy(t -> t.f0)
                .sum(1)
                // 显示编号为1~12之前，是由于运行电脑12个逻辑处理器，默认模拟并行线程为12个。
                .print();
        // 流需要触发执行
        env.execute();
    }
}
