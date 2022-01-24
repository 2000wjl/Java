package com.zyhwjl.Druid;

import com.zyhwjl.dao.Customer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/1/6 16:57
 */
public interface CustomerDao {
    void insert (Connection conn, Customer customer);
    void delete (Connection conn,int id);
    void update (Connection conn,Customer customer);
    Customer getCustomerById (Connection conn,int id);
    List<? extends Customer> getAll (Connection conn);
    Long getCount (Connection conn);
    Date getMaxBirth (Connection conn);
}
