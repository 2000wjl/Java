package com.zyhwjl.jdbc.jdbc4_blob;

import com.zyhwjl.jdbc.jdbc3_bean.Customer;
import org.junit.Test;

import java.io.*;
import java.sql.*;

import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.CloseResource;
import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.getConnection;

/**
 * @Description : 对数据库Blob类型数据的插入、读取操作。
 *                常见问题：
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2021/12/25 13:20
 */
public class PreparedStatementBlob {
    /**
    * @Description: 向数据库插入Blob类型数据
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2021/12/25
    */
    @Test
    public void PreparedStatementBlobInsertTest() {
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream fis = null;
        try {
            conn = getConnection();
            String sql = "insert into customers(name,email,birth,photo) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,"陈奕迅");
            ps.setObject(2,"chen@zyhwjl.cn");
            ps.setObject(3,"1999-01-01");

            fis = new FileInputStream("img.jpg");

            ps.setBlob(4,fis);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CloseResource(conn,ps);
            try {
                if(fis!=null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
    /**
    * @Description: 从数据库获取Blob类型数据
    * @Param: []
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2021/12/25
    */
    @Test
    public void PreparedStatementBlobReadTest() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        FileOutputStream fos = null;
        try {
            conn = getConnection();
            String sql = "select * from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,16);
            rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");
                System.out.println(new Customer(id,name,email,birth));

                Blob photo = rs.getBlob("photo");
                InputStream binaryStream = photo.getBinaryStream();
    //            造文件
                File fl = new File("img.jpg");
                fos = new FileOutputStream(fl);
                byte[] buffer = new byte[20];
                int len;
                while((len = binaryStream.read(buffer))!=-1) {
                    fos.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        CloseResource(conn,ps);
        try {
            if(rs!=null)
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(fos!=null)
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
