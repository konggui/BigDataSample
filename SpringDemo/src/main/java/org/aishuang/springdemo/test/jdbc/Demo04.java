package org.aishuang.springdemo.test.jdbc;

import org.aishuang.springdemo.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc: 查询数据封装到Map集合中
 * @program: MapPOI
 * @packagename: org.aishuang.springdemo.test.jdbc
 * @author: aishuang
 * @date: 2023-05-06 15:49
 */
public class Demo04 {
    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = JdbcUtils.getConnection();
        // 创建SQL语句
        String sql = "select * from user";
        // 预编译SQL语句，获取预编译对象
        PreparedStatement preStat = conn.prepareStatement(sql);
        // sql语句中没有参数，所以可以直接执行
        ResultSet resultSet = preStat.executeQuery();
        // 一张表中所有数据，只对应一个List集合，所以在循环外创建List
        ArrayList<Map<String, Object>> list = new ArrayList<>();

        while (resultSet.next()) {
            // 有几条数据，就创建几个map集合，所以map集合的创建应该放在循环里面
            HashMap<String, Object> map = new HashMap<>();
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            map.put("id", id);
            map.put("username", username);
            map.put("password", password);

            // 将map集合添加到list中
            list.add(map);
        }

        for (Map<String, Object> map : list) {
            System.out.println(map);
        }

        JdbcUtils.close(resultSet, preStat, conn);
    }
}
