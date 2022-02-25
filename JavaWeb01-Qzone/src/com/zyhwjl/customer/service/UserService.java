package com.zyhwjl.customer.service;

import com.zyhwjl.customer.domain.Contexts;
import com.zyhwjl.customer.domain.Customers;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 11:40
 */
public interface UserService {
    List<Customers> getFri(Integer id) throws Exception;
}
