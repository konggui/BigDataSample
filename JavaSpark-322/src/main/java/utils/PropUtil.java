package utils;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: utils
 * @author: aishuang
 * @date: 2023-05-12 23:43
 */
public final class PropUtil {
    public static Properties loadProp(String filePath) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(filePath));
        return prop;
    }

    public static Properties loadResourcesProp(String fileName) throws IOException {
        Properties prop = new Properties();
        prop.load(PropUtil.class.getClassLoader().getResourceAsStream(fileName));
        return prop;
    }
}
