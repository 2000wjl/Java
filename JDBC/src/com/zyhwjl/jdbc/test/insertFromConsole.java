package com.zyhwjl.jdbc.test;

import com.zyhwjl.jdbc.jdbc3_bean.Customer;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.CloseResource;
import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.getConnection;

/**
 * @Description : 插入练习
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2021/12/24 17:41
 */
public class insertFromConsole {

    @Test
    public void insertTest() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name = scanner.nextLine();
        System.out.println("请输入email：");
        String email = scanner.nextLine();
        System.out.println("请输入出生日期：");
        String birth = scanner.nextLine();
        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        insert(sql,name,email,birth);
    }

    public void insert(String sql,Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            CloseResource(conn,ps);
        }



    }
}
