package com.zyhwjl.myssm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/10 12:40
 */
public class JBUtils {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    public static Connection createConnection() throws Exception {
        Connection conn = threadLocal.get();
        if(conn==null){
            conn=getConnection();
            threadLocal.set(conn);
            return conn;
        }
        return conn;
    }


    public static Connection getConnection() throws Exception {
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
//
//        Properties pros = new Properties();
//        pros.load(is);

//        String url = pros.getProperty("url");
//        String user = pros.getProperty("user");
//        String password = pros.getProperty("password");
//        String driverClass = pros.getProperty("driverClass");
        String url = "jdbc:mysql://bt2.zyhwjl.cn:3306/javaweb?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        String user = "zyhwjl";
        String password = "zyhwjl";
        String driverClass = "com.mysql.cj.jdbc.Driver";

        Class.forName(driverClass);

        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
    }
}
