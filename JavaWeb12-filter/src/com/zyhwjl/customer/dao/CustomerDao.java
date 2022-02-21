package com.zyhwjl.customer.dao;

import com.zyhwjl.customer.domain.Customers;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/10 12:58
 */
public interface CustomerDao {
    List<Customers> queryAll(String keyword);
    List<Customers> queryPage(Integer pageN,String keyword);
    Customers query(Integer id) ;
    void update(Object... args);
    void delete(Integer id) ;
    void add(Object... args);
}
