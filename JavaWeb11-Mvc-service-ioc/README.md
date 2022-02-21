通过`applicationContext.xml`配置`CustomerController`、`CustomerDao`、`customerService`之间的关系。

通过中央控制器`DispatcherServlet`调用`BeanFactory`将对应的`Bean`反射至对应位置

降低各组件(控制、Dao、服务)间的耦合性