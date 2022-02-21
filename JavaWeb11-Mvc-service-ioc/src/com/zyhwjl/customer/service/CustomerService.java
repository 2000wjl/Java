package com.zyhwjl.customer.service;

import com.zyhwjl.customer.domain.Customers;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/21 12:13
 */
public interface CustomerService {
    List<Customers> getCustomerList(String keyword, Integer pageN);
    void addCustomer(Customers customers);
    Customers getCustomerById(Integer id);
    void delCustomer(Integer id);
    Integer getPageCount(String keyword);
    void updateCustomer(Customers customers);
}
