package com.zyhwjl.myssm.filter;

import com.zyhwjl.myssm.utils.JBUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/22 14:29
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Connection conn = null;
        try {
            conn = JBUtils.createConnection();
            conn.setAutoCommit(false);
//            System.out.println("开启事务");

//            放行
            filterChain.doFilter(servletRequest, servletResponse);

//            System.out.println("提交数据");
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
//                System.out.println("执行回滚");
                if(conn!=null){
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
