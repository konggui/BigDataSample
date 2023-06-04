package org.aishuang.springdemo.test;

import org.aishuang.springdemo.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @desc:
 * @program: MapPOI
 * @packagename: org.aishuang.springdemo.test
 * @author: aishuang
 * @date: 2023-05-06 14:15
 */
public class TransferTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try {
            // 获取连接
            conn = JdbcUtils.getConnection();
            // 获取SQL执行对象
            statement = conn.createStatement();
            // 开启事务
            conn.setAutoCommit(false);
            String inSql = "update account set money = money + 2000 where aid = 1";
            int i = statement.executeUpdate(inSql);
            System.out.println("转入SQL的行数是：" + i);
            String outSql = "update account set money = money - 2000 where aid = 2";
            int j = statement.executeUpdate(outSql);
            System.out.println("转出SQL影响的行数是：" + j);

            // 提交事务
            conn.commit();
            System.out.println("转账成功！~");
        } catch (Exception e) {
            e.printStackTrace();
            // 如果代码执行到这里，说明前面的转账有问题，转账失败
            System.out.println("转账失败!");
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } finally {
            // 一定执行的代码: 释放资源
            JdbcUtils.close(statement, conn);
        }
    }
}
