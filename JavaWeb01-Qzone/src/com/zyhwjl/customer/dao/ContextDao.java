package com.zyhwjl.customer.dao;

import com.zyhwjl.customer.domain.Contexts;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 11:47
 */
public interface ContextDao {
    List<Contexts> queryContextList(Integer id) throws Exception;
    void newDairy(Contexts contexts) throws Exception;
    void delete(Integer id) throws Exception;
}
