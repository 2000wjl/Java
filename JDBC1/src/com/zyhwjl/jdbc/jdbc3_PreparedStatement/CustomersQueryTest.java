package com.zyhwjl.jdbc.jdbc3_PreparedStatement;

import com.zyhwjl.jdbc.jdbc3_bean.Customer;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.CloseResource;
import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.getConnection;

/**
 * @Description : 对于Customer表的通用查询操作
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2021/12/24 13:08
 */
public class CustomersQueryTest {

    @Test
    public void Query01() {
        String sql = "select name,email from customers where name = ?";
        System.out.println(CommonQuery(sql,"成龙"));
    }

    public Customer CommonQuery(String sql,Object ... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
//        1.获取连接
            conn = getConnection();
//        2.预编译sql语句 返回PreparedStatement实例
            ps = conn.prepareStatement(sql);
//        3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
//        4.执行
//            获取数据集
            ResultSet rs = ps.executeQuery();
//            获取数据集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
//            获取元数据的列数
            int columnCount = rsmd.getColumnCount();
//            数据集的next()方法，指向下一行数据，存在则返回并继续指向下一行，否则返回false
            if(rs.next()){
                Customer customer = new Customer();
                for (int i = 0; i < columnCount; i++) {
//                    获取指定列的值
                    Object columnValue = rs.getObject(i+1);
//                    获取指定列名(表中的，非别名)
                    String columnName = rsmd.getColumnName(i+1);
//                    获取实体类的指定字段反射
                    Field field = Customer.class.getDeclaredField(columnName);
//                    允许反射访问字段
                    field.setAccessible(true);
//                    反射赋值
                    field.set(customer,columnValue);
                }
    //              结果
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//        5.关闭连接
            CloseResource(conn,ps);
        }
        return null;

    }
}
