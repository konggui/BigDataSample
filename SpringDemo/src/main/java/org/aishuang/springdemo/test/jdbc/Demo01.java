package org.aishuang.springdemo.test.jdbc;

import org.aishuang.springdemo.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.Statement;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: org.aishuang.springdemo.test.jdbc
 * @author: aishuang
 * @date: 2023-05-06 14:41
 */
public class Demo01 {
    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = JdbcUtils.getConnection();
        // 开启事务，自动提交为false
        conn.setAutoCommit(false);
        // 执行SQL语句
        Statement stat = conn.createStatement();
        String sql = "update user set password = '888888' where id = 1";
        int i = stat.executeUpdate(sql);
        System.out.println("影响的行数为：" + i);

        // 关闭事务：提交
//        conn.commit();
        // 关闭事务：回滚
         conn.rollback();
        // 释放资源
        JdbcUtils.close(stat, conn);
    }
}
