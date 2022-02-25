package com.zyhwjl.customer.dao;

import com.zyhwjl.customer.domain.Contexts;
import com.zyhwjl.myssm.base.BaseDao;
import com.zyhwjl.myssm.utils.JBUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 11:48
 */
public class ContextDaoImpl extends BaseDao<Contexts> implements ContextDao{
    @Override
    public List<Contexts> queryContextList(Integer id) throws Exception {
        Connection conn = JBUtils.createConnection();
        String sql = "select id,title,datetime from contexts where cusid = ?";
        return queryList(conn, sql, id);
    }

    @Override
    public void newDairy(Contexts contexts) throws Exception {
        Connection conn = JBUtils.createConnection();
        String sql = "insert into contexts(title,datetime,cusid) values(?,?,?)";
        update(conn,sql,contexts.getTitle(),contexts.getDatetime(),contexts.getCusID());
    }

    @Override
    public void delete(Integer id) throws Exception {
        Connection conn = JBUtils.createConnection();
        String sql = "delete from contexts where id = ?";
        update(conn,sql,id);
    }

}
