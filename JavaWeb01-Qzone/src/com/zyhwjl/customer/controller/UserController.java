package com.zyhwjl.customer.controller;

import com.zyhwjl.customer.domain.Contexts;
import com.zyhwjl.customer.domain.Customers;
import com.zyhwjl.customer.service.impl.ContextServiceImpl;
import com.zyhwjl.customer.service.impl.CustomerServiceImpl;
import com.zyhwjl.customer.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 11:02
 */
public class UserController {

    ContextServiceImpl contextService = null;
    UserServiceImpl userService = null;

    public String delete(Integer id) throws Exception {
        contextService.deleteDairy(id);
        return "redirect:user.do";
    }

    public String newDairy(String title,HttpSession session) throws Exception {
        if(title==null){
            return "newDairy";
        }
        Integer id = (Integer) session.getAttribute("id");
        contextService.newDairy(id,title);
        return "redirect:user.do";
    }

    public String index(HttpServletRequest req, HttpSession session,String showId,String showName) throws Exception {
        Object name = session.getAttribute("name");

        List<Customers> fris = userService.getFri((Integer) session.getAttribute("id"));

        Integer id = (Integer) session.getAttribute("id");
        if(showId!=null&&!"".equals(showId)){
            id = Integer.valueOf(showId);
        }
        List<Contexts> contexts = contextService.getContext(id);

        if(showName!=null&&!"".equals(showName)){
            req.setAttribute("showName",showName);
        }

        req.setAttribute("fris",fris);
        req.setAttribute("contexts",contexts);

        return "user";
    }
}
