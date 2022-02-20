package com.zyhwjl.jdbc.test;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.CloseResource;
import static com.zyhwjl.jdbc.jdbc3_util.JDBCUtils.getConnection;

/**
 * @Description : 练习
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2021/12/24 18:17
 */
public class insertIntoExamstudent {

    @Test
    public void qdTest() throws Exception {
        String sql1 = "select * from examstudent where ExamCard = ?";
        String sql2 = "delete from examstudent where ExamCard = ?";
        System.out.println("请输入学生的考号");
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        queryanddelete(sql1,sql2,num);
    }

    public boolean queryanddelete(String sql1,String sql2,String num) throws Exception {
        Connection conn = null;
        PreparedStatement psq = null;
        PreparedStatement psd = null;
        try {
            conn = getConnection();
            psq = conn.prepareStatement(sql1);
            psq.setObject(1,num);
            ResultSet rs = psq.executeQuery();
            if(!rs.next()){
                System.out.println("查无此人");
                return false;
            }else{
                psd = conn.prepareStatement(sql2);
                psd.setObject(1,num);
                psd.execute();
                System.out.println("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(conn!=null)
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(psq!=null)
                psq.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(psd!=null)
                psd.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Test
    public void queryTest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您要输入的类型：");
        System.out.println("a：准考证号");
        System.out.println("b：身份证号");
        String type = scanner.nextLine();
        String num = "";
        String sql = "";
        if(Objects.equals(type, "a")){
            System.out.println("请输入准考证号：");
            num = scanner.nextLine();
            sql = "select * from examstudent where ExamCard = ?";
        }else if(Objects.equals(type, "b")){
            System.out.println("请输入身份证号：");
            num = scanner.nextLine();
            sql = "select * from examstudent where IDCard = ?";
        }else{
            System.out.println("您的输入有误！请重新进入程序。");
        }
        examStudent es = query(sql, num);
        if(es!=null){
            System.out.println("============查询结果============");
            System.out.println(es);
        }else {
            System.out.println("查无此人，请重新进入程序。");
        }
    }

    public examStudent query(String sql,String num){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1,num);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            if(rs.next()){
                examStudent es = new examStudent();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    String columnName = rsmd.getColumnLabel(i+1);
                    Object columnValue = rs.getObject(i+1);
                    Field field = examStudent.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(es,columnValue);
                }
                return es;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CloseResource(conn,ps);
        }
        return null;
    }

    @Test
    public void insertTest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入考生类型（4/6）");
        String type = scanner.nextLine();
        System.out.println("请输入考生身份证号码");
        String idcard = scanner.nextLine();
        System.out.println("请输入考生准考证号码");
        String examcard = scanner.nextLine();
        System.out.println("请输入考生姓名");
        String name = scanner.nextLine();
        System.out.println("请输入考生区域");
        String location = scanner.nextLine();
        System.out.println("请输入考生成绩");
        String grade = scanner.nextLine();
        String sql = "insert into examstudent(Type,IDCard,ExamCard,StudentName,Location,Grade) values(?,?,?,?,?,?)";
        insert(sql,type,idcard,examcard,name,location,grade);
    }

    public void insert(String sql,Object...args) {
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
