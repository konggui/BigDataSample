package util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: util
 * @author: aishuang
 * @date: 2023-04-16 13:29
 */
public class JsonUtil {

    public static boolean isJson(String content) {
        if(StringUtils.isEmpty(content)){
            return false;
        }
        boolean isJsonObject = true;
        boolean isJsonArray = true;
        try {
            JSONObject.parseObject(content);
        } catch (Exception e) {
            isJsonObject = false;
        }
        try {
            JSONObject.parseArray(content);
        } catch (Exception e) {
            isJsonArray = false;
        }
        if(!isJsonObject && !isJsonArray){
            return false;
        }
        return true;
    }

    public static boolean isJsonObject(String content) {
        if(StringUtils.isEmpty(content)){
            return false;
        }
        try {
            JSONObject.parseObject(content);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static <T> Object toBean(String json, Class<T> type) {
        return JSONObject.parseObject(json, type);
    }
}
