package com.zyhwjl.customer.dao;

import com.zyhwjl.customer.domain.Customers;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/10 12:58
 */
public interface CustomerDao {
    List<Customers> queryAll(String keyword) throws Exception;
    List<Customers> queryPage(Integer pageN,String keyword) throws Exception;
    Customers query(Integer id) throws Exception;
    void update(Object... args) throws Exception;
    void delete(Integer id) throws Exception;
    void add(Object... args) throws Exception;
}
