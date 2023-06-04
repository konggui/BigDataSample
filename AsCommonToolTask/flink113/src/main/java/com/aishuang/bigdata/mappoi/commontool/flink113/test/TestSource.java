package com.aishuang.bigdata.mappoi.commontool.flink113.test;

import com.aishuang.bigdata.mappoi.commontool.flink113.constant.ConfigConstant;
import com.aishuang.bigdata.mappoi.commontool.flink113.util.PropertiesUtil;
import com.aishuang.bigdata.mappoi.commontool.flink113.util.SourceUtil;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Properties;


/**
 * @desc:
 * @program: MapPOI
 * @packagename: com.aishuang.bigdata.mappoi.commontool.flink113.test
 * @author: aishuang
 * @date: 2022-11-13 23:55
 */
public class TestSource {
    public static void main(String[] args) throws Exception {
        Properties properties = PropertiesUtil.loadProp(args[0]);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SourceUtil.getSourceFromKafka(env, ConfigConstant.OPERATION_LOG_TOPIC_REFER, properties).print();
        // SourceUtil.getSourceFromHdfs(env, "hdfs://node01:8020/fileSystemData06").print();
        // SourceUtil.getSourceFromCustom(env, 10, 10).print();
        env.execute();
    }
}
