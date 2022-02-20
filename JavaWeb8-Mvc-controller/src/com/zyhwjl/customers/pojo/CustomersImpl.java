package com.zyhwjl.customers.pojo;

import com.zyhwjl.customers.domain.Customers;
import com.zyhwjl.myssm.base.BaseDao;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static com.zyhwjl.myssm.utils.JBUtils.getConnection;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/10 12:37
 */
public class CustomersImpl extends BaseDao<Customers> implements CustomersDao {
    @Override
    public List<Customers> queryAll(String keyword)  {
        try {
            Connection conn = getConnection();
            String sql = "select * from customers where name like ? ";
            return queryList(conn,sql,"%"+keyword+"%");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customers> queryPage(Integer pageN,String keyword) {
        try {
            Connection conn = getConnection();
            String sql = "select * from customers where name like ? limit ? , 5 ";
            return queryList(conn,sql,"%"+keyword+"%",(pageN-1)*5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customers query(Integer id) {
        try {
            Connection conn = getConnection();
            String sql = "select * from customers where id = ?";
            return query(conn,sql,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(Object... args) {
        try {
            Connection conn = getConnection();
            String sql = "UPDATE customers SET name=?, password=? where id = ?";
            update(conn,sql,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id)  {
        try {
            Connection conn = getConnection();
            String sql = "Delete from customers where id = ?";
            update(conn,sql,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Object... args)  {
        try {
            Connection conn = getConnection();
            String sql = "insert into customers(name,password) VALUES(?,?)";
            update(conn,sql,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
