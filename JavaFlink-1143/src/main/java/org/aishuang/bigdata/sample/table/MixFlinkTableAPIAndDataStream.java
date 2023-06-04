package org.aishuang.bigdata.sample.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @desc:
 * @program: BigDataSample
 * @packagename: org.aishuang.bigdata.sample.table
 * @author: aishuang
 * @date: 2023-06-04 22:03
 */
public class MixFlinkTableAPIAndDataStream {
    public static void main(String[] args) {
        // 1.获取stream的执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 2.创建表执行环境
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
        // 3.获取数据
        tableEnv.fromValues(
            "A,x1,2022-02-02 12:00:00",
            "B,x1,2022-02-02 12:00:00",
            "C,x1,2022-02-02 12:00:00",
            "D,x1,2022-02-02 12:00:00",
            "E,x1,2022-02-02 12:00:00",
            "F,x1,2022-02-02 12:00:00"
        ).map(v -> {
            v.split
        })
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ClickLog{
        private String user;
        private String url;
        private String cTime;
    }
}
