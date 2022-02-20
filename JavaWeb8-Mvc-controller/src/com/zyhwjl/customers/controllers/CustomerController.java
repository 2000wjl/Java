package com.zyhwjl.customers.controllers;

import com.mysql.cj.Session;
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
public class CustomerController {

    private final CustomersImpl customersDao = new CustomersImpl();

    private String editP(Integer id,String name,String password) throws IOException {
        customersDao.update(name,password,id);
        return "redirect:customer.do";
    }

    private String editG(Integer id,HttpServletRequest req) throws IOException {
        if(id!=null){
            Customers customer = customersDao.query(id);
//            HttpSession session = req.getSession();
            req.setAttribute("Customers",customer);
            return "edit";
        }
        return null;
    }

    private String delete(String id) throws IOException {
        customersDao.delete(id);
        return "redirect:customer.do";
    }

    private String add(String name,String password) throws IOException {
        customersDao.add(name,password);
        return "redirect:customer.do";
    }

    private String index(String keyword, Integer pageN, String sign,HttpSession session,HttpServletRequest req) throws IOException {
        if(pageN==null){
            pageN = 1;
        }
        int pageS = 5 ;

        if("search".equals(sign)){
//            是查询
            pageN = 1;
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
        return "index";
    }
}
