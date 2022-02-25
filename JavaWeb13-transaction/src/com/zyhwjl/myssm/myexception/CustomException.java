package com.zyhwjl.myssm.myexception;

/**
 * @Description :
 * @Author : ZYHWJL E-mail:zyhwjl@zyhwjl.cn
 * @Date : 2022/2/22 15:16
 */
public class CustomException extends RuntimeException{

    //无参构造方法
    public CustomException(){

        super();
    }

    //有参的构造方法
    public CustomException(String message){
        super(message);

    }

    // 用指定的详细信息和原因构造一个新的异常
    public CustomException(String message, Throwable cause){

        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public CustomException(Throwable cause) {

        super(cause);
    }

}
