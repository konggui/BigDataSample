package com.aishuang.bigdata.mappoi.commontool.flink113.demo;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: com.aishuang.bigdata.mappoi.commontool.flink113.demo
 * @author: aishuang
 * @date: 2022-11-09 22:29
 */
public class BatchWordCount {
    private static String inputPath = "E:\\workspace\\MapPOI\\MapPOICommonToolTask\\flink113\\input\\words.txt";
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> source = env.readTextFile(inputPath);
        source.flatMap((String line , Collector<Tuple2<String, Long>> out) -> {
                    Arrays.stream(line.split(" "))
                            .forEach(value -> out.collect(Tuple2.of(value, 1L)));
                })
                // 当lambda使用Java泛型时，由于泛型擦除的存在，需要显示的声明类型信息
                .returns(Types.TUPLE(Types.STRING, Types.LONG))
                .groupBy(0)
                .sum(1)
                .print();
    }
}
