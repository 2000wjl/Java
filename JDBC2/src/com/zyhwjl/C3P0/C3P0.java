package com.zyhwjl.C3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/1/18 19:08
 */
public class C3P0 {
    /**
    * @Description: 方式一：硬编码创建C3P0连接池
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2022/1/21
    */
    @Test
    public void c3p0test01() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://bt2.zyhwjl.cn:3306/test" );
        cpds.setUser("zyhwjl");
        cpds.setPassword("zyhwjl");

        cpds.setInitialPoolSize(5);

        Connection conn = cpds.getConnection();
        System.out.println(conn);

        DataSources.destroy(cpds);
    }
    /**
    * @Description: 方式二：通过配置文件创建c3p0连接池
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2022/1/21
    */
    @Test
    public void c3p0test02() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }

}
