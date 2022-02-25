package com.zyhwjl.myssm.listeners;

import com.zyhwjl.myssm.ioc.BeanFactory;
import com.zyhwjl.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/22 16:22
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        String path = application.getInitParameter("ContextConfigLocation");
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        application.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
