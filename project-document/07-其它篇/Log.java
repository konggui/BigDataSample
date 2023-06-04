package pers.aishuang.flink.demo.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;

/**
 * @Desc: id、src、category、step、process_txt、errorcode、timestamp、status、raw_data、error_message、traceid、、、
 * @Author: AiShuang
 * @Date: 2022/3/31 22:27
 */
@Setter
@Getter
public class Log {
    /**
     * id 唯一标识
     */
    @JSONField(name = "id", ordinal = 0)
    private String id;

    /**
     * step 数据处理步骤
     */
    @JSONField(name = "step", ordinal = 1)
    private String step;

    /**
     * raw_data 原始数据
     */
    @JSONField(name = "raw_data", ordinal = 2)
    private String rawData;

    /**
     * src 数据来源
     */
    @JSONField(name = "src", ordinal = 3)
    private String src;

    /**
     * category 数据种类
     */
    @JSONField(name = "category", ordinal = 4)
    private String category="asar";

    /**
     * traceid 数据追踪Id
     */
    @JSONField(name = "traceid", ordinal = 5)
    private String traceId;

    /**
     * timestamp 日志产生的时间戳
     */
    @JSONField(name = "timestamp", ordinal = 6)
    private Long timestamp;

    /**
     * errorcode 错误码
     */
    @JSONField(name = "errorcode", ordinal = 7)
    private String errorCode;

    /**
     * error_message 错误信息
     */
    @JSONField(name = "error_message", ordinal = 8)
    private String errorMessage;

    /**
     * process_text 处理信息
     */
    @JSONField(name = "process_text", ordinal = 9)
    private String processText;

    /**
     * extra_text 其它信息
     */
    @JSONField(name = "extra_text", ordinal = 10)
    private String extraText;

    @JSONField(serialize = false)
    private String test = "123";

    @Override
    public String toString() {
        return JSON.toJSONString(this, FEATURES);
    }

    private static final SerializerFeature[] FEATURES = {
            //输出空置字段
            SerializerFeature.WriteMapNullValue,
            //list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            //数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            //Boolean字段如果为null，输出为false,而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            //字符类型字段如果为null,输出为"",而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    public static void main(String[] args) {
        System.out.println(new Log());
    }
}
