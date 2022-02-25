package com.zyhwjl.myssm.myspringmvc;

import com.zyhwjl.myssm.ioc.BeanFactory;
import com.zyhwjl.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/14 19:40
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public void init() throws ServletException {
        super.init();
//        beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if(beanFactoryObj!=null){
            beanFactory = (BeanFactory) beanFactoryObj;
        }else {
            throw new RuntimeException("中央控制器初始化失败，原因：监听器未创建beanFactory");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("UTF-8");
        String servletPath = req.getServletPath();
        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastIndexOf);

        if("".equals(servletPath)){
            resp.sendRedirect("login.do");
        }

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = req.getParameter("operate");

        if(operate==null || "".equals(operate)){
            operate = "index";
        }

        Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
        for (Method m : methods){
            String name = m.getName();
            if(operate.equals(name)){
//                    1.参数处理
//                    获取参数
                    Parameter[] parameters = m.getParameters();
//                    存储参数值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        String parameterName = parameters[i].getName();
                        if("req".equals(parameterName)){
                            parameterValues[i] = req;
                        }else if("resp".equals(parameterName)){
                            parameterValues[i] = resp;
                        }else if("session".equals(parameterName)){
                            parameterValues[i] = req.getSession();
                        }else {
                            String parameterValue = req.getParameter(parameterName);
                            Object parameterObj = parameterValue;

                            if(parameterObj!=null){
                                if(parameters[i].getType().getName().equals("java.lang.Integer")){
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }

                            parameterValues[i] = parameterObj;
                        }
                    }



//                    2.Controller组件中的方法调用
                    m.setAccessible(true);
                Object returnObj = null;
                try {
                    returnObj = m.invoke(controllerBeanObj, parameterValues);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                    3.视图处理
                    String methodReturnStr = (String) returnObj;
                    if(methodReturnStr.startsWith("redirect:")){
                        String redirectTarget = methodReturnStr.substring("redirect:".length());
                        resp.sendRedirect(redirectTarget);
                    }else{
                        super.processTemplate(methodReturnStr, req, resp);
                    }

                return;
            }
        }
        throw new RuntimeException("参数异常！");
    }
}
