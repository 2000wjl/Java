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
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/10 15:48
 */
@WebServlet("/index")
public class ThymeleafServlet extends ViewBaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
    * @Description: pageN:目标页码，pageS:分页大小，pageL:总页码
    * @Param: [req, resp]
    * @return: void
    * @Author: ZYHWJL
    * @Date: 2022/2/12
    */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        CustomersImpl customerDao = new CustomersImpl();
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

        int pageL = customerDao.queryAll(keyword).size() / 5 + 1 ;
        List<Customers> customers = customerDao.queryPage(pageN,keyword);
        session.setAttribute("Customers",customers);
        req.setAttribute("pageN",pageN);
        req.setAttribute("pageL",pageL);
        req.setAttribute("pageS",pageS);
        super.processTemplate("index",req,resp);
    }
}
