package com.zyhwjl.customer.service.impl;

import com.zyhwjl.customer.dao.loginDaoImpl;
import com.zyhwjl.customer.domain.Customers;
import com.zyhwjl.customer.service.loginService;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 9:19
 */
public class loginServiceImpl implements loginService {

    loginDaoImpl loginDao = null;

    @Override
    public Customers isUser(String name, String password) throws Exception {
        return loginDao.queryCustomer(name);
    }

    @Override
    public String register(String name, String password) throws Exception {
        Customers customers = loginDao.queryCustomer(name);
        if(customers==null){
            loginDao.addCustomer(name,password);
            return "注册成功";
        }else{
            return "用户名被占用";
        }
    }
}
