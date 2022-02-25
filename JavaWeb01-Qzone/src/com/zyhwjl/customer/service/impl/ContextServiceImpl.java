package com.zyhwjl.customer.service.impl;

import com.zyhwjl.customer.dao.ContextDaoImpl;
import com.zyhwjl.customer.domain.Contexts;
import com.zyhwjl.customer.service.ContextService;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/23 11:48
 */
public class ContextServiceImpl implements ContextService {

    ContextDaoImpl contextDao = null;

    @Override
    public List<Contexts> getContext(Integer id) throws Exception {
        return contextDao.queryContextList(id);
    }

    @Override
    public void newDairy(Integer id ,String title) throws Exception {
        contextDao.newDairy(new Contexts(title, now(),id));
    }

    @Override
    public void deleteDairy(Integer id) throws Exception {
        contextDao.delete(id);
    }
}
