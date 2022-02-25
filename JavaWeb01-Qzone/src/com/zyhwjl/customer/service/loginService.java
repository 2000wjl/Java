package com.zyhwjl.customer.service;

import com.zyhwjl.customer.domain.Customers;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 9:19
 */
public interface loginService {
    Customers isUser(String name, String password) throws Exception;
    String register(String name,String password) throws Exception;
}
