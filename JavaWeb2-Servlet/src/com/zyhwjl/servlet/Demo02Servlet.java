package com.zyhwjl.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description : Servlet的生命周期：创建、初始化、服务、服务......销毁
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/9 21:22
 */
public class Demo02Servlet extends HttpServlet {

    public Demo02Servlet(){
        System.out.println("正在创建.....");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化......");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在服务......");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁......");
    }
}
