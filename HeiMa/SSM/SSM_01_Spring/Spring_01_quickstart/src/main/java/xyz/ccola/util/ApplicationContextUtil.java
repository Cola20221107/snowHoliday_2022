package xyz.ccola.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

/**
 * @ Name: ApplicationContextUtil
 * @ Author: Cola
 * @ Time: 2022/12/20 13:55
 * @ Description: ApplicationContextUtil
 */
public class ApplicationContextUtil {
    /**
     * 通过加载类路径的方式获取 Context
     * @return Context
     */
    public static ApplicationContext getApplicationContext(){
        ApplicationContext context = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        return Objects.requireNonNull(context);
    }
}
