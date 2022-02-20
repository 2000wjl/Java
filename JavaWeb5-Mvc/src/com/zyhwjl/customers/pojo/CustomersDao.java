package com.zyhwjl.customers.pojo;

import com.zyhwjl.customers.domain.Customers;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/10 12:58
 */
public interface CustomersDao {
    List<Customers> queryAll(String keyword);
    List<Customers> queryPage(Integer pageN,String keyword);
    Customers query(String id) ;
    void update(Object... args);
    void delete(String id) ;
    void add(Object... args);
}
