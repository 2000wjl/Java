package com.zyhwjl.customer.service.impl;

import com.zyhwjl.customer.dao.UserDaoImpl;
import com.zyhwjl.customer.domain.Contexts;
import com.zyhwjl.customer.domain.Customers;
import com.zyhwjl.customer.service.UserService;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 11:42
 */
public class UserServiceImpl implements UserService {

    UserDaoImpl userDao = null;

    @Override
    public List<Customers> getFri(Integer id) throws Exception {
        return userDao.queryFriList(id);
    }
}
