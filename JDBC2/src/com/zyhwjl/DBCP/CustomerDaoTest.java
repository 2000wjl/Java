package com.zyhwjl.DBCP;

import com.zyhwjl.dao.Customer;
import org.junit.Test;

import java.sql.Connection;
import java.util.Date;

import static com.zyhwjl.DBCP.JDBCUtils.CloseResource;
import static com.zyhwjl.DBCP.JDBCUtils.getConnection;

/**
* @Description: C3P0技术的测试
* @Param: 
* @return: 
* @Author: ZYHWJL
* @Date: 2022/1/21
*/
public class CustomerDaoTest {

    CustomerDaoImpl dao = new CustomerDaoImpl();

    @Test
    public void insert(){
        Connection conn = null;
        Customer customer = null;
        try {
            conn = getConnection();
            customer = new Customer(93,"刘关张","liu@123.com",new Date());
            dao.insert(conn,customer);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            CloseResource(conn,null);
        }
    }

    @Test
    public void delete() throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            dao.delete(conn,93);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            CloseResource(conn,null);
        }
    }

    @Test
    public void update() {
        Connection conn = null;
        Customer customer = null;
        try {
            conn = getConnection();
            customer = new Customer(93,"刘关张兄弟","liu@123.com",new Date());
            dao.update(conn,customer);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            CloseResource(conn,null);
        }
    }

    @Test
    public void getCustomerById() throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println(dao.getCustomerById(conn, 16));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            CloseResource(conn,null);
        }
    }

    @Test
    public void getAll() throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            dao.getAll(conn).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            CloseResource(conn,null);
        }
    }

    @Test
    public void getCount() throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println(dao.getCount(conn));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CloseResource(conn,null);

        }
    }

    @Test
    public void getMaxBirth() throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println(dao.getMaxBirth(conn));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            CloseResource(conn,null);
        }
    }
}