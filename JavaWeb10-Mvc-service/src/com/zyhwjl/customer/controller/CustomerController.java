package com.zyhwjl.customer.controller;

import com.mysql.cj.util.StringUtils;
import com.zyhwjl.customer.domain.Customers;
import com.zyhwjl.customer.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/12 19:59
 */
public class CustomerController {

//    private final CustomersImpl customersDao = new CustomersImpl();
    CustomerServiceImpl customerService = new CustomerServiceImpl();

    private String editP(Integer id,String name,String password) throws IOException {
        customerService.updateCustomer(new Customers(id,name,password));
        return "redirect:customer.do";
    }

    private String editG(Integer id,HttpServletRequest req) throws IOException {
        if(id!=null){
            Customers customer = customerService.getCustomerById(id);
//            HttpSession session = req.getSession();
            req.setAttribute("Customers",customer);
            return "edit";
        }
        return null;
    }

    private String delete(Integer id) throws IOException {
        customerService.delCustomer(id);
        return "redirect:customer.do";
    }

    private String add(String name,String password) throws IOException {
        customerService.addCustomer(new Customers(name,password));
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

        int pageL = customerService.getPageCount(keyword);
        List<Customers> customers = customerService.getCustomerList(keyword,pageN);
        session.setAttribute("Customers",customers);
        req.setAttribute("pageN",pageN);
        req.setAttribute("pageL",pageL);
        req.setAttribute("pageS",pageS);
        return "index";
    }
}
