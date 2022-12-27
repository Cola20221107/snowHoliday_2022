package xyz.ccola.common;

/**
 * @ Name: BaseContext
 * @ Author: Cola
 * @ Time: 2022/12/27 20:11
 * @ Description: 基于 ThreadLocal 封装工具类，用户保存和获取当前登录用户 id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id 当前登录用户的 id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return ThreadLocal 中的值
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
