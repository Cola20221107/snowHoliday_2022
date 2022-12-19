package xyz.ccola.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.ccola.interceptor.LoginInterceptor;

import java.util.ArrayList;

/**
 * @ Name: ManagerConfig
 * @ Author: Cola
 * @ Time: 2022/12/19 17:49
 * @ Description: ManagerConfig
 */
@Configuration
public class ManagerConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 拦截器
     * @param registry 注册中心
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
        放行静态资源
         */
        ArrayList<String> urls = new ArrayList<>();
        urls.add("/**/*.js");
        urls.add("/**/*.css");
        urls.add("/**/*.jpg");
        urls.add("/**/*.png");
        urls.add("/**/*.html");
        /*
        放行必要请求
         */
        urls.add("/admin/user/login");
        urls.add("/admin/user/logout");

        /*
        注册
         */
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(urls);
    }
}

