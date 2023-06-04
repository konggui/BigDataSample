package org.aishuang.springdemo.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @desc: 用来操作JDBC的工具类
 * @program: MapPOI
 * @packagename: org.aishuang.springdemo.utils
 * @author: aishuang
 * @date: 2023-05-06 14:17
 */
public class JdbcUtils {
    // 私有构造方法
    private JdbcUtils() {}
    private static String driverClass;
    private static String url;
    private static String user;
    private static String password;

    // 静态代码块
    static {
        try {
            // 注册驱动，在程序最开始的时候，只需要注册一次 -> 静态代码块
            // 使用Properties获取配置文件中的信息
            Properties prop = new Properties();
            // 获取关联配置文件的输入流
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 读取输入流中的配置信息
            prop.load(in);
            in.close();
            driverClass = prop.getProperty("driverClass");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");

            // 注册驱动
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static void close(ResultSet rs, Statement stat, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void close(Statement stat, Connection conn) {
        close(null, stat, conn);
    }
}
