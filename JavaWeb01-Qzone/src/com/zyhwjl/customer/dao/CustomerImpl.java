package com.zyhwjl.customer.dao;

import com.zyhwjl.customer.domain.Customers;
import com.zyhwjl.myssm.base.BaseDao;

import java.sql.Connection;
import java.util.List;

import static com.zyhwjl.myssm.utils.JBUtils.createConnection;
import static com.zyhwjl.myssm.utils.JBUtils.getConnection;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/10 12:37
 */
public class CustomerImpl extends BaseDao<Customers> implements CustomerDao {

    @Override
    public List<Customers> queryAll(String keyword) throws Exception {
            Connection conn = createConnection();
            String sql = "select * from customers where name like ? ";
            return queryList(conn,sql,"%"+keyword+"%");
//        return null;
    }

    @Override
    public List<Customers> queryPage(Integer pageN,String keyword) throws Exception {
            Connection conn = createConnection();
            String sql = "select * from customers where name like ? limit ? , 5 ";
            return queryList(conn,sql,"%"+keyword+"%",(pageN-1)*5);
    }

    @Override
    public Customers query(Integer id) throws Exception {
            Connection conn = createConnection();
            String sql = "select * from customers where id = ?";
            return query(conn,sql,id);
    }


    @Override
    public void update(Object... args) throws Exception {
            Connection conn = createConnection();
            String sql = "UPDATE customers SET name=?, password=? where id = ?";
            update(conn,sql,args);
    }

    @Override
    public void delete(Integer id) throws Exception {
            Connection conn = createConnection();
            String sql = "Delete from customers where id = ?";
            update(conn,sql,id);
    }

    @Override
    public void add(Object... args) throws Exception {
            Connection conn = createConnection();
            String sql = "insert into customers(name,password) VALUES(?,?)";
            update(conn,sql,args);

    }
}
