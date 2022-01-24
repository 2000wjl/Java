package com.zyhwjl.C3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/1/21 3:04
 */
public class JDBCUtils {
    /**
    * @Description: 使用c3p0技术获取数据库连接
    * @Param: []
    * @return: java.sql.Connection
    * @Author: ZYHWJL
    * @Date: 2022/1/21
    */
    //不好的写法：每调用getConnection方法都会重复生成数据库连接池
//    public static Connection getConnection() throws SQLException {
//        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
//        Connection conn = cpds.getConnection();
//        return conn;
//    }
//    正确写法：仅提供一个连接池
    static private ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    public static Connection getConnection() throws SQLException {
        Connection conn = cpds.getConnection();
        return conn;
    }
    public static void CloseResource(Connection conn, Statement ps){
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps!=null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
