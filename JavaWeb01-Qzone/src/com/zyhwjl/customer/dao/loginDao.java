package com.zyhwjl.customer.dao;

import com.zyhwjl.customer.domain.Customers;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 9:20
 */
public interface loginDao {
    Customers queryCustomer(String name) throws Exception;
    void addCustomer(String name,String password) throws Exception;
}
