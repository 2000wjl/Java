package com.zyhwjl.customer.service;

import com.zyhwjl.customer.domain.Customers;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/21 12:13
 */
public interface CustomerService {
    List<Customers> getCustomerList(String keyword, Integer pageN) throws Exception;
    void addCustomer(Customers customers) throws Exception;
    Customers getCustomerById(Integer id) throws Exception;
    void delCustomer(Integer id) throws Exception;
    Integer getPageCount(String keyword) throws Exception;
    void updateCustomer(Customers customers) throws Exception;
}
