package com.zyhwjl.jdbc.jdbc3_PreparedStatement;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.*;


/**
 * @Description : PreparedStatementTest
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2021/12/23 19:06
 */
public class PreparedStatementTest {

    /***
    * @Description: PreparedStatement的插入操作测试
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2021/12/24
    */
    @Test
    public void insertTest(){
        InputStream is = null;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //1.读取配置文件中的4个基本信息
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

            Properties pros = new Properties();
            pros.load(is);

            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String driverClass = pros.getProperty("driverClass");

            //2.加载驱动
            Class.forName(driverClass);

            //3.获取连接
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn);

//        4.预编译sql语句，返回PreparedStatement的实例
            String sql = "insert into customers(name,email,birth) values(?,?,?)";
            ps = conn.prepareStatement(sql);
//        5.填充占位符
            ps.setObject(1,"刘德华");
            ps.setObject(2,"liu@zyhwjl.cn");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("1996-05-06");
            ps.setDate(3,new java.sql.Date(date.getTime()));

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(ps!=null)
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(conn!=null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(is!=null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

    /**
    * @Description: 使用Utils通过PreparedStatement删除操作测试
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2021/12/24
    */
    @Test
    public void DeleteTest() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
//        1.获取连接
            conn = getConnection();
//        2.预编译sql语句 返回PreparedStatement实例
            String sql = "Delete From customers where name = ?";
            ps = conn.prepareStatement(sql);
//        3.填充占位符
            ps.setObject(1,"刘德华");
//        4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//        5.关闭资源
            CloseResource(conn,ps);

        }

    }

    /**
    * @Description: 通用增删改操作
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2021/12/24
    */
    @Test
    public void doUpdateTest(){
        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        CommonUpdate(sql,"刘德华","liu@liu.com","2000-05-06");

//        String sql = "delete from customers where name = ?";
//        CommonUpdate(sql,"刘德华");
    }

    /**
    * @Description: 通用增删改操作的执行
    * @Param: [sql, args]
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2021/12/24
    */
    public void CommonUpdate(String sql,Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
//        1.获取连接
            conn = getConnection();
//        2.预编译sql语句 返回PreparedStatement实例
            ps = conn.prepareStatement(sql);
//        3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
//        4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//        5.关闭资源
            CloseResource(conn,ps);

        }

    }
}
