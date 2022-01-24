package com.zyhwjl.Dbutil;

import com.zyhwjl.Druid.JDBCUtils;
import com.zyhwjl.dao.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description : commons-dbutils是Apache组织提供的一个开源的JDBC工具类库，封装了针对于数据库操作的增删改查操作
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/1/24 4:31
 */
public class QueryRunnerTest {
    /**
    * @Description: 插入测试
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2022/1/24
    */
    @Test
    public void QueryRunnerTest01() throws Exception {
        QueryRunner qr = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        System.out.println(qr.update(conn, sql, "testDruidRunner", "test@test.cn", "2000-05-06"));
    }
   /**
   * @Description: BeanHandler测试，返回单条查询记录
   * @Param: []
   * @return: void
   * @Author: ZYHWJL
   * @Date: 2022/1/24
   */
    @Test
    public void BeanHandlerTest() throws Exception {
        QueryRunner qr = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id,name,email,birth from customers where id = ?";
        BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
        Customer query = qr.query(conn, sql, handler, 55);
        System.out.println(query);
    }
    /**
    * @Description: BenaListHandler测试，返回多条查询记录
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2022/1/24
    */
    @Test
    public void BenaListHandlerTest() throws Exception {
        QueryRunner qr = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id,name,email,birth from customers where id <= ?";
        BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
        List<Customer> query = qr.query(conn, sql, handler, 55);
        query.forEach(System.out::println);
    }
    /**
    * @Description: MapHandler测试，MapHandler是ResultSetHandler接口的实现类，对应表中的一条记录
    * 将字段及相应字段的值作为map中的key和value
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2022/1/24
    */
    @Test
    public void MapHandlerTest() throws Exception {
        QueryRunner qr = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id,name,email,birth from customers where id = ?";
        MapHandler handler = new MapHandler();
        Map<String, Object> query = qr.query(conn, sql, handler, 55);
        System.out.println(query);
    }
    /**
     * @Description: MapListHandler测试，MapListHandler是ResultSetHandler接口的实现类，对应表中的多条记录
     * 将字段及相应字段的值作为map中的key和value
     * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2022/1/24
    */
    @Test
    public void MapListHandlerTest() throws Exception {
        QueryRunner qr = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id,name,email,birth from customers where id <= ?";
        MapListHandler handler = new MapListHandler();
        List<Map<String, Object>> query = qr.query(conn, sql, handler, 55);
        query.forEach(System.out::println);
    }
    /**
    * @Description: ScalarHandler测试，用于查询特殊值
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2022/1/24
    */
    @Test
    public void ScalarHandlerTest() throws Exception {
        QueryRunner qr = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select count(*) from customers where id <= ?";
        ScalarHandler handler = new ScalarHandler();
        Object query = qr.query(conn, sql, handler, 55);
        System.out.println(query);
    }
    /**
    * @Description: 自定义ResultSetHandler
    * @Param: 
    * @return: 
    * @Author: ZYHWJL
    * @Date: 2022/1/24
    */
    @Test
    public void MyResultSetHandlerTest() throws Exception {
        QueryRunner qr = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id,name,email,birth from customers where id = ?";
        ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>() {
            @Override
            public Customer handle(ResultSet resultSet) throws SQLException {
//                return null;
//                return new Customer(10,"zyhwjl","zyhwjl@1.cn",new Date());
                if(resultSet.next()){
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setBirth(resultSet.getDate("birth"));
                    return customer;
                }
                return null;
            }
        };
        Object query = qr.query(conn, sql, handler, 55);
        System.out.println(query);
    }
}