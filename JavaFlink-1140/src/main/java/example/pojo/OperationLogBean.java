package example.pojo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import util.JsonUtil;

import java.io.Serializable;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: example.entity
 * @author: aishuang
 * @date: 2023-04-16 09:50
 */
@Setter
@Getter
@NoArgsConstructor
public class OperationLogBean implements Serializable {
    /**
     * id 唯一标识
     */
    @JSONField(name = "id", ordinal = 0)
    private static String id;

    /**
     * category 数据种类
     */
    @JSONField(name = "category", ordinal = 1)
    private static String category;

    /**
     * step 数据处理步骤
     */
    @JSONField(name = "step", ordinal = 2)
    private String step;


    /**
     * raw_data 原始数据
     */
    @JSONField(name = "raw_data", ordinal = 3)
    private String rawData;

    /**
     * src 数据来源
     */
    @JSONField(name = "src", ordinal = 4)
    private String src;

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

    public OperationLogBean(String id, String category) {
        this.id = id;
        this.category = category;
    }

    public static OperationLogBean of(String operationLog) {
        JSONObject json = JSONObject.parseObject(operationLog);
        OperationLogBean bean = new OperationLogBean(json.getString("id"), json.getString("category"));
        bean.setStep(json.getString("step"));
        bean.setRawData(json.getString("raw_data"));
        bean.setSrc(json.getString("src"));
        bean.setTraceId(json.getString("traceid"));
        bean.setTimestamp(Long.valueOf(json.getString("timestamp")));
        bean.setErrorCode(json.getString("errorcode"));
        bean.setErrorMessage(json.getString("errormessage"));
        bean.setProcessText(json.getString("process_text"));
        bean.setExtraText(json.getString("extra_text"));
        return bean;
    }

    public static boolean isOperationLog(String line) {
        if (JsonUtil.isJsonObject(line)) {
            JSONObject json = JSONObject.parseObject(line);
            if (StringUtils.isNotBlank(json.getString("id")) && StringUtils.isNotBlank(json.getString("category"))) {
                return true;
            }
        }
        return false;
    }


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
}
