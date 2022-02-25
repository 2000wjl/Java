package com.zyhwjl.customer.service;

import com.zyhwjl.customer.domain.Contexts;

import java.util.List;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 11:48
 */
public interface ContextService {
    List<Contexts> getContext(Integer id) throws Exception;
    void newDairy(Integer id , String title) throws Exception;
    void deleteDairy(Integer id) throws Exception;
}
