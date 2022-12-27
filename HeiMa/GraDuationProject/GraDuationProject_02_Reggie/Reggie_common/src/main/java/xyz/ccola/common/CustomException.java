package xyz.ccola.common;

/**
 * @ Name: CustomException
 * @ Author: Cola
 * @ Time: 2022/12/27 20:14
 * @ Description: 自定义业务异常类
 */
public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}
