package com.zyhwjl.DBCP;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/1/21 3:19
 */
public class DbcpTest {
    /**
    * @Description: 方式一：通过硬编码获取dbcp连接池
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2022/1/21
    */
    @Test
    public void dbcptest01() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://bt2.zyhwjl.cn:3306/test");
        dataSource.setUsername("zyhwjl");
        dataSource.setPassword("zyhwjl");

        dataSource.setInitialSize(10);
        dataSource.setMaxTotal(5);

        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
    /**
    * @Description: 方式二：通过配置文件获取数据库连接池
    * @Param:
    * @return:
    * @Author: ZYHWJL
    * @Date: 2022/1/21
    */
    @Test
    public void dbcptest02() throws Exception {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("dbcp.properties");
        properties.load(fis);
        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
