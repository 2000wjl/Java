package com.zyhwjl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description : 关于Requests保存作用域的测试
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/10 17:17
 */
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("uname","lina");
//        方式一：重定向，无法获取--多次请求
        resp.sendRedirect("demo02");
//        方式二：服务器内部转发，可获取--一次请求
//        req.getRequestDispatcher("demo02").forward(req,resp);
    }
}
