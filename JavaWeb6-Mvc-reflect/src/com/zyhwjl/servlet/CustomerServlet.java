package com.zyhwjl.servlet;

import com.mysql.cj.util.StringUtils;
import com.zyhwjl.customers.domain.Customers;
import com.zyhwjl.customers.pojo.CustomersImpl;
import com.zyhwjl.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/12 19:59
 */
@WebServlet("/customer.do")
public class CustomerServlet extends ViewBaseServlet {

    private CustomersImpl customersDao = new CustomersImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String operate = req.getParameter("operate");

        if(operate==null || "".equals(operate)){
            operate = "index";
        }

        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method m : methods){
            if(operate.equals(m.getName())){
                try {
                    m.invoke(this, req,resp);
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("操作非法");

//        switch (operate){
//            case "index" :
//                index(req, resp);
//                break;
//            case "add" :
//                add(req, resp);
//                break;
//            case "delete":
//                delete(req, resp);
//                break;
//            case "editG":
//                editG(req, resp);
//                break;
//            case "editP":
//                editP(req, resp);
//                break;
//            default:
//                error(req, resp);
//        }
    }

    private void error(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.processTemplate("error", req, resp);
    }
    private void editP(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        customersDao.update(name,password,id);
        resp.sendRedirect("/customer.do");
    }

    private void editG(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id!=null&&!"".equals(id)){
            Customers customer = customersDao.query(id);
//            HttpSession session = req.getSession();
            req.setAttribute("Customers",customer);
            super.processTemplate("edit",req,resp);
        }else{
            resp.sendRedirect("/customer.do");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customersDao.delete(req.getParameter("id"));
        resp.sendRedirect("/customer.do");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customersDao.add(req.getParameter("name"),req.getParameter("password"));
        resp.sendRedirect("/customer.do");
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int pageN = 1 ;
        int pageS = 5 ;
        if (req.getParameter("pageN") != null && !"".equals(req.getParameter("pageN"))){
            pageN = parseInt(req.getParameter("pageN"));
        }

        String keyword = "";
        String sign = req.getParameter("sign");
        if(sign != null && "search".equals(sign)){
//            是查询
            pageN = 1;
            keyword = req.getParameter("keyword");
            if(StringUtils.isNullOrEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
            session.setAttribute("sign", sign);
        }else {
//            不是查询
            session.removeAttribute("sign");
            Object keywordObj =  session.getAttribute("keyword");
            if (keywordObj != null){
                keyword = (String) keywordObj;
                if(req.getParameter("pageN") == null){
                    keyword = "";
                }
            }else {
                keyword = "" ;
            }
        }

        int pageL = customersDao.queryAll(keyword).size() / 5 + 1 ;
        List<Customers> customers = customersDao.queryPage(pageN,keyword);
        session.setAttribute("Customers",customers);
        req.setAttribute("pageN",pageN);
        req.setAttribute("pageL",pageL);
        req.setAttribute("pageS",pageS);
        super.processTemplate("index",req,resp);
    }
}
