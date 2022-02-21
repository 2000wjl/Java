package com.zyhwjl.customer.service.impl;

import com.zyhwjl.customer.dao.CustomerImpl;
import com.zyhwjl.customer.domain.Customers;
import com.zyhwjl.customer.service.CustomerService;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/21 12:13
 */
public class CustomerServiceImpl implements CustomerService {

    CustomerImpl CustomerDao = new CustomerImpl();

    @Override
    public List<Customers> getCustomerList(String keyword, Integer pageN) {
        return CustomerDao.queryPage(pageN,keyword);
    }

    @Override
    public void addCustomer(Customers customers) {
        CustomerDao.add(customers);
    }

    @Override
    public Customers getCustomerById(Integer id) {
        return CustomerDao.query(id);
    }

    @Override
    public void delCustomer(Integer id) {
        CustomerDao.delete(id);
    }

    @Override
    public Integer getPageCount(String keyword) {
        return CustomerDao.queryAll(keyword).size()/5+1;
    }

    @Override
    public void updateCustomer(Customers customers) {
        CustomerDao.update(customers);
    }
}
