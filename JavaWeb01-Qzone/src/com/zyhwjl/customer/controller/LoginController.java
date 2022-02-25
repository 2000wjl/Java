package com.zyhwjl.customer.controller;

import com.zyhwjl.customer.domain.Customers;
import com.zyhwjl.customer.service.impl.loginServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 9:18
 */
public class LoginController {

    loginServiceImpl loginService = null;


    public String register(String name,String password, HttpServletRequest req, HttpSession session) throws Exception {
        if(name==null||password==null){
            if(req.getAttribute("operate")!=null){
                req.setAttribute("flag","输入有误");
            }
            return "register";
        }

        String register = loginService.register(name, password);
        if("注册成功".equals(register)){
            session.setAttribute("name",name);
            req.setAttribute("flag","注册成功");
            return "login";
        }else {
            req.setAttribute("flag",register);
            return "register";
        }
    }

    public String index(String name, String password, HttpServletRequest req, HttpSession session) throws Exception {

        if(name==null||password==null){
            return "login";
        }

        session.setAttribute("name",name);
        Customers customers = loginService.isUser(name,password);

        if(customers!=null){
            if(password.equals(customers.getPassword())){
                session.setAttribute("id",customers.getId());
//                req.setAttribute("flag","登陆成功");
                return "redirect:user.do";
//                return "login";
            }else{
                req.setAttribute("flag","密码错误");
            }
        }else {
            req.setAttribute("flag","用户不存在");
        }

        return "login";

    }
}
