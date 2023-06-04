package com.aishuang.bigdata.mappoi.commontool.flink113.util;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.TimeUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: com.aishuang.bigdata.mappoi.commontool.flink113.util
 * @author: aishuang
 * @date: 2022-11-13 23:48
 */
public final class SourceUtil {
    public static DataStream<String> getSourceFromHdfs(StreamExecutionEnvironment env, String inputPath) {
        return env.readTextFile(inputPath);
    }

    public static DataStream<String> getSourceFromKafka(StreamExecutionEnvironment env, String topicRefer, Properties properties) {
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                properties.getProperty(buildConfigKey(topicRefer, ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG)));
        consumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,
                Integer.parseInt(properties.getProperty(buildConfigKey(topicRefer, ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG), "10000")));

        consumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,
                properties.getProperty(buildConfigKey(topicRefer, ConsumerConfig.GROUP_ID_CONFIG)));
        consumerProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                properties.getProperty(buildConfigKey(topicRefer, ConsumerConfig.AUTO_OFFSET_RESET_CONFIG)));
        // 100*1024*1024=104857600
        consumerProperties.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG,
                Integer.parseInt(properties.getProperty(buildConfigKey(topicRefer, ConsumerConfig.FETCH_MAX_BYTES_CONFIG), "104857600")));

        return env.addSource(new FlinkKafkaConsumer<String>(
                properties.getProperty(buildConfigKey(topicRefer, "topic.name")),
                new SimpleStringSchema(),
                consumerProperties));
    }

    public static String buildConfigKey(String topicRefer, String propertyName) {
        return topicRefer + "." + propertyName;
    }


    public static DataStream<String> getSourceFromCustom(StreamExecutionEnvironment env, int parallelism, int intervalSecond) {
        return env.addSource(new ParallelSourceFunction<String>() {
            private static final long serialVersionUID = -8290466408006748429L;
                private boolean running = true;
                private Random random = new Random();

            @Override
            public void run(SourceContext<String> ctx) throws Exception {
                while (running) {
                    ctx.collect(String.valueOf(random.nextInt()));
                    Thread.sleep(TimeUnit.SECONDS.toMillis(intervalSecond));
                    System.out.println(System.currentTimeMillis());
                }
            }

            @Override
            public void cancel() {
                running = false;
            }
        });
    }
}
