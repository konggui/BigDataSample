package com.aishuang.bigdata.mappoi.commontool.flink113.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: com.aishuang.bigdata.mappoi.commontool.flink113.util
 * @author: aishuang
 * @date: 2022-11-14 21:57
 */
public class PropertiesUtil {
    public static Properties loadProp(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        return properties;
    }
}
