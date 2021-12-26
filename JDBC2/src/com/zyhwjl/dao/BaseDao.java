package com.zyhwjl.dao;

import org.junit.Test;

import java.beans.Customizer;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.zyhwjl.utils.JDBCUtils.CloseResource;
import static com.zyhwjl.utils.JDBCUtils.getConnection;

/**
 * @Description : 封装针对于数据表的通用操作
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2021/12/25 17:48
 */
public class BaseDao {
    // 通用的增删改操作---version 2.0 （考虑上事务）
    public <T> void update(Connection conn, String sql, Object...args) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
               ps.setObject(i+1,args[i]);
            }
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseResource(null,ps);
        }
    }

    /**
    * @Description: 对于。。的测试---》通用的查询操作，用于返回数据表中的一条记录（version 2.0：考虑上事务）
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2021/12/25
    */
    @Test
    public void query1Test() throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String sql = "select id,name,email,birth from customers where id = ?";
            Customer customer = new Customer();

            System.out.println(query1(customer.getClass(),conn,sql,1));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn!=null)
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    // 通用的查询操作，用于返回数据表中的一条记录（version 2.0：考虑上事务）
    public <T> T query1(Class<T> clazz,Connection conn,String sql,Object...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            if(rs.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnName = rsmd.getColumnLabel(i + 1);
                    Field field=t.getClass().getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CloseResource(null,ps);
            try {
                if(rs!=null)
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    /**
    * @Description: 对于。。的测试--》通用的查询操作，用于返回数据表中的多条记录构成的集合（version 2.0：考虑上事务）
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2021/12/26
    */
    @Test
    public void querylistTest() throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            Customer customer = new Customer();
            String sql = "select id,name,email,birth from customers where 1 = ?";
            querylist(customer.getClass(),conn,sql, 1).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 通用的查询操作，用于返回数据表中的多条记录构成的集合（version 2.0：考虑上事务）
    public <T> List<T> querylist(Class<T> clazz, Connection conn, String sql, Object...args) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            ArrayList<T> list = new ArrayList<>();
            while(rs.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnName = rsmd.getColumnLabel(i + 1);
                    Field field = t.getClass().getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            CloseResource(null,ps);
            try {
                if(rs!=null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return null;
    }


    //用于查询特殊值的通用的方法
}
