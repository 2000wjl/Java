package com.zyhwjl.DBCP;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/1/21 3:04
 */
public class JDBCUtils {
    /**
    * @Description: 使用静态代码块使数据库池仅加载一次
    * @Author: ZYHWJL
    * @Date: 2022/1/23
    */
    private static DataSource dataSource;
    static{
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream("dbcp.properties");
            properties.load(fis);
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
    * @Description: 使用DBCP技术获取数据库连接
    * @Param: []
    * @return: java.sql.Connection
    * @Author: ZYHWJL
    * @Date: 2022/1/21
    */
    public static Connection getConnection() throws Exception {
        Connection conn = dataSource.getConnection();
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
