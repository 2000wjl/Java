package com.zyhwjl.myssm.myspringmvc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/14 19:40
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private final Map<String,Object> beanMap = new HashMap<>();

    public DispatcherServlet(){

    }

    public void init() throws ServletException {
        super.init();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            NodeList nodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node beanNode = nodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Object beanObj = Class.forName(className).newInstance();
                    beanMap.put(beanId, beanObj);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String servletPath = req.getServletPath();
        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastIndexOf);

        Object controllerBeanObj = beanMap.get(servletPath);

        String operate = req.getParameter("operate");

        if(operate==null || "".equals(operate)){
            operate = "index";
        }

        Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
        for (Method m : methods){
            String name = m.getName();
            if(operate.equals(name)){
                try {
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
                    Object returnObj = m.invoke(controllerBeanObj, parameterValues);

//                    3.视图处理
                    String methodReturnStr = (String) returnObj;
                    if(methodReturnStr.startsWith("redirect:")){
                        String redirectTarget = methodReturnStr.substring("redirect:".length());
                        resp.sendRedirect(redirectTarget);
                    }else{
                        super.processTemplate(methodReturnStr, req, resp);
                    }

                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        throw new RuntimeException("参数异常！");
    }
}
