package com.zyhwjl.Druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/1/23 5:07
 */
public class DruidTest {
    @Test
    public void DruidTest01() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://bt2.zyhwjl.cn:3306/test");
        dataSource.setUsername("zyhwjl");
        dataSource.setPassword("zyhwjl");
        DruidPooledConnection conn = dataSource.getConnection();
        System.out.println(conn);
    }
    @Test
    public void DruidTest() throws Exception {
        Properties properties = new Properties();
        FileInputStream fi = new FileInputStream("druid.properties");
        properties.load(fi);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
