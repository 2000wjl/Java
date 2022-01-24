package com.zyhwjl.DBCP;

import com.zyhwjl.dao.Customer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/1/6 17:04
 */
public class CustomerDaoImpl extends BaseDao<Customer> implements CustomerDao {



    @Override
    public void insert(Connection conn, Customer customer) {
        String sql = "insert into customers(id,name,email,birth) values(?,?,?,?)";
        try {
            update(conn,sql,customer.getId(),customer.getName(),customer.getEmail(),customer.getBirth());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        try {
            update(conn,sql,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Connection conn, Customer customer) {
        String sql = "update customers set name = ? , email = ? , birth = ? where id = ?";
        try {
            update(conn,sql,customer.getName(),customer.getEmail(),customer.getBirth(),customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "select id,name,email,birth from customers where id = ?";
        Customer customer = new Customer();
        try {
            return (Customer) query1(conn,sql,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers where 1 = ?";
        Customer customer = new Customer();
        try {
            return querylist(conn,sql,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers where 1 = ?";
        try {
            return (Long) QueryAny(conn, sql, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "SELECT MAX(birth) from customers where 1 = ?";
        try {
            return (Date) QueryAny(conn, sql, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
