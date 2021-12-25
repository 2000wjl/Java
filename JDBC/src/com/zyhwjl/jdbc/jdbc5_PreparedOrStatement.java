package com.zyhwjl.jdbc;

import com.zyhwjl.jdbc.jdbc3_util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.CloseResource;
import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.getConnection;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2021/12/25 14:11
 */
public class jdbc5_PreparedOrStatement {

    /**
    * @Description: 10815ms
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2021/12/25
    */
    @Test
    public void testInsert2() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            long start = System.currentTimeMillis();

            conn = getConnection();
//            String sql = "insert into goods(name) values(?)";
//            ps = conn.prepareStatement(sql);
            Statement st = conn.createStatement();
            for(int i = 1;i <= 200;i++){
//                ps.setObject(1, "name_" + i);
                String sql = "insert into goods(name)values('name_" + i + "')";
                st.execute(sql);
//                ps.execute();
            }

            long end = System.currentTimeMillis();

            System.out.println("花费的时间为：" + (end - start));//20000:83065
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            CloseResource(conn, ps);

        }

    }

    /**
    * @Description: 11061ms
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2021/12/25
    */
    @Test
    public void testInsert1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            long start = System.currentTimeMillis();

            conn = getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            for(int i = 1;i <= 200;i++){
                ps.setObject(1, "name_" + i);

                ps.execute();
            }

            long end = System.currentTimeMillis();

            System.out.println("花费的时间为：" + (end - start));//20000:83065
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            CloseResource(conn, ps);

        }

    }

}
