package org.aishuang.springdemo.test.jdbc;

import org.aishuang.springdemo.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: org.aishuang.springdemo.test.jdbc
 * @author: aishuang
 * @date: 2023-05-06 16:09
 */
public class Demo02 {
    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = JdbcUtils.getConnection();
        // 创建SQL语句
        String sql = "select * from user";
        // 预编译SQL语句，获取预编译对象
        PreparedStatement preStat = conn.prepareStatement(sql);
        // sql语句中没有参数，所以可以直接执行
        ResultSet resultSet = preStat.executeQuery();

        ArrayList<User> list = new ArrayList<>();

        while(resultSet.next()) {
            // 创建User对象
            User user = new User();
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);

            list.add(user);
        }

        for(User user : list) {
            System.out.println(user);
        }

        JdbcUtils.close(resultSet, preStat, conn);
    }
}
