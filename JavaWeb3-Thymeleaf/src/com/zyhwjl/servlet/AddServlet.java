package com.zyhwjl.servlet;

import com.zyhwjl.customers.pojo.CustomersImpl;
import com.zyhwjl.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/11 14:42
 */
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomersImpl customersDao = new CustomersImpl();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        customersDao.add(name,password);
        resp.sendRedirect("/index2");
    }
}
