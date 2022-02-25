package com.zyhwjl.customer.dao;

import com.zyhwjl.customer.domain.Customers;
import com.zyhwjl.myssm.base.BaseDao;

import java.sql.Connection;

import static com.zyhwjl.myssm.utils.JBUtils.createConnection;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 9:20
 */
public class loginDaoImpl extends BaseDao<Customers> implements loginDao{
    @Override
    public Customers queryCustomer(String name) throws Exception {
        Connection conn = createConnection();
        String sql = "select * from customers where name = ?";
        return query(conn,sql,name);
    }

    @Override
    public void addCustomer(String name,String password) throws Exception {
        Connection conn = createConnection();
        String sql = "insert into customers(name,password) values(?,?) ";
        update(conn,sql,name,password);
    }
}
