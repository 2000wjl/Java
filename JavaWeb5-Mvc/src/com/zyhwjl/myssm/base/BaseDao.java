package com.zyhwjl.myssm.base;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/10 12:36
 */
public class BaseDao<T> {

    private Class<T> clazz = null;

    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramtype = (ParameterizedType) genericSuperclass;

        Type[] actualTypeArguments = paramtype.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }

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
        }
    }

    public T query(Connection conn,String sql,Object...args) throws Exception {
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1,args[i]);
        }
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        if (rs.next()){
            T t = clazz.newInstance();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                Object object = rs.getObject(i + 1);
                String columnName = rsmd.getColumnName(i + 1);
                Field field = t.getClass().getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(t,object);
            }
            return t;
        }


        return null;
    }

    public List<T> queryList(Connection conn,String sql,Object...args) throws Exception {
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1,args[i]);
        }
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        ArrayList<T> List = new ArrayList<>();
        while (rs.next()){
            T t = clazz.newInstance();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                Object object = rs.getObject(i + 1);
                String columnName = rsmd.getColumnName(i + 1);
                Field field = t.getClass().getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(t,object);
            }
            List.add(t);
        }
        return List;
    }
}
