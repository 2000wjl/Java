package com.zyhwjl.customer.dao;

import com.zyhwjl.customer.domain.Contexts;
import com.zyhwjl.customer.domain.Customers;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 11:39
 */
public interface UserDao {
    List<Customers> queryFriList(Integer id) throws Exception;
}
