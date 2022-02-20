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
 * @Date : 2022/2/10 19:39
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomersImpl customerDao = new CustomersImpl();
        String id = req.getParameter("id");
        if(id!=null&&!"".equals(id)){
            Customers customer = customerDao.query(id);
//            HttpSession session = req.getSession();
            req.setAttribute("Customers",customer);
            super.processTemplate("edit",req,resp);
        }else{
            resp.sendRedirect("/index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomersImpl customerDao = new CustomersImpl();
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        customerDao.update(name,password,id);

//        List<Customers> customers = customerDao.queryAll();

//        HttpSession session = req.getSession();
//        session.setAttribute("Customers",customers);

        resp.sendRedirect("/index");
    }
}
