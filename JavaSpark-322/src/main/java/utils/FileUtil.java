package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @desc:
 * absolute path 绝对路径
 * relative path 相对路径
 *
 * @program: MapPOI
 * @packagename: utils
 * @author: aishuang
 * @date: 2023-04-13 23:30
 */
public class FileUtil {

    public static Boolean isExistsLocalFilePath(String relativePath) {
        return new File(relativePath).exists();
    }

    public static  void removeLocalFilePath(String relativePath) throws IOException {
        FileUtils.deleteDirectory(new File(relativePath));
    }
}
