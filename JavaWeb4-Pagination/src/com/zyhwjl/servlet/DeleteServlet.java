package com.zyhwjl.servlet;

import com.zyhwjl.customers.domain.Customers;
import com.zyhwjl.customers.pojo.CustomersImpl;
import com.zyhwjl.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/11 14:42
 */
@WebServlet("/delete.do")
public class DeleteServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomersImpl customerDao = new CustomersImpl();
        customerDao.delete(req.getParameter("id"));

//        List<Customers> customers = customerDao.queryAll();

//        HttpSession session = req.getSession();
//        session.setAttribute("Customers",customers);

        resp.sendRedirect("/index");
    }
}
