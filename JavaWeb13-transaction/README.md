#####过滤器

通过过滤器实现事务处理，主要原理为在线程中（实际不是，而是`ThreadLocal`）创建数据库连接，所有数据库操作都由此连接进行处理。

后在前端过滤器禁用自动提交，并try catch事务内容，若异常则回滚此连接。

难点在于`ThreadLocal`创建，及事务中的异常处理。

#####监听器

listeners包监听动作，其中ContextLoaderListener中实现ServletContextListener监听Servlet上下文。

在此创建BeanFactory并将其存储在application作用域，不在中央控制器中创建以提升系统响应速度。


在监听器中通过读取Servlet配置文件为IOC容器指定配置文件