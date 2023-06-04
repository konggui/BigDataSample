package org.aishuang.springdemo.test.jdbc;

import org.aishuang.springdemo.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: org.aishuang.springdemo.test.jdbc
 * @author: aishuang
 * @date: 2023-05-06 15:26
 */
public class Demo03PreparedStatementLogin {
    public static void main(String[] args) throws Exception {
        // 键盘录入用户名和密码
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();

        // 使用JDBC连接并查询数据库
        // 获取连接
        Connection conn = JdbcUtils.getConnection();

        // 创建SQL语句
        // String sql = "select * from user where username = ? and password = ?";
        String sql = "select * from user where username = ?";

        // 预编译
        PreparedStatement preStat = conn.prepareStatement(sql);

        // 给?赋值
        preStat.setString(1, username);
        //preStat.setString(2, password);

        // 执行SQL语句，获取结构集
        ResultSet resultSet = preStat.executeQuery();

        // 判断登录是否成功，就是看结果集中是否有数据
        if (resultSet.next()) {
            System.out.println("登录成功！~");
            // 获取登录成功时使用的用户名和密码，打印出来
            // 获取结果集中的数据
            String username2 = resultSet.getString("username");
            String password2 = resultSet.getString("password");
            System.out.println("请牢记你的用户名：" + username2 + "，和密码：" + password2);
        } else {
            System.out.println("登录失败！~");
        }

        // 释放资源
        JdbcUtils.close(resultSet, preStat, conn);
    }
}
