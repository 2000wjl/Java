package com.zyhwjl.customer.dao;

import com.zyhwjl.customer.domain.Contexts;
import com.zyhwjl.customer.domain.Customers;
import com.zyhwjl.myssm.base.BaseDao;
import com.zyhwjl.myssm.utils.JBUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 11:44
 */
public class UserDaoImpl extends BaseDao<Customers> implements UserDao{

    @Override
    public List<Customers> queryFriList(Integer id) throws Exception {
        Connection conn = JBUtils.createConnection();
        String sql = "select customers.id,customers.name,customers.`password` from relation ,customers where relation.id = ? and relation.cusid = customers.id";
        return queryList(conn,sql,id);
    }

}
