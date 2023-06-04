package com.aishuang.bigdata.mappoi.commontool.flink113.demo;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import org.apache.flink.util.TimeUtils;

import java.util.Arrays;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: com.aishuang.bigdata.mappoi.commontool.flink113.demo
 * @author: aishuang
 * @date: 2022-11-09 23:11
 */
public class UnboundedStreamWordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.socketTextStream("192.168.88.161", 7777)
                .flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
                    Arrays.stream(line.split(" ")).forEach(value -> out.collect(Tuple2.of(value, 1L)));
                })
                // map和flatmap的lambda表达式需要后面加下return声明返回类型
                .returns(Types.TUPLE(Types.STRING, Types.LONG))
                .keyBy(t -> t.f0)
                .sum(1)
                .print();
        env.execute();
    }
}
