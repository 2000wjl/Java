#此Module存在NullPoint异常

原因为为Servlet添加中央控制器dispatcherServlet，通过其将具体方法反射至相对应的Controller时。 
在调用CustomerController时，由于`com.zyhwjl.customers.controllers;`不再是Servlet组件，故为调用其中`init()`方法导致。

##在下个JavaWeb中将修复此问题。

处理方式：P41-P42