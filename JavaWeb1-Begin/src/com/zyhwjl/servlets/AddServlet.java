package com.zyhwjl.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/1/29 16:19
 */
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        System.out.println(name);
        System.out.println(password);

        try {
            add(name,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(String...args) throws Exception{
        try {

            String url = "jdbc:mysql://bt2.zyhwjl.cn:3306/javaweb";
            String user = "zyhwjl";
            String password = "zyhwjl";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            QueryRunner qr = new QueryRunner();
            String sql = "insert into customers(name,password) values(?,?)";
            System.out.println(qr.update(conn, sql, args));
            System.out.println("添加成功");
            DbUtils.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
