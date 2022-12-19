package xyz.ccola.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.ccola.common.JwtUtil;
import xyz.ccola.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ Name: LoginInterceptor
 * @ Author: Cola
 * @ Time: 2022/12/19 17:48
 * @ Description: LoginInterceptor
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    /**
     * 拦截请求后进行 Token 校验
     * @param request 请求
     * @param response 响应
     * @param handler handler
     * @return 鉴权结果
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("正在解析 Token");
        /*
        获取请求体中的 token 并解析
         */
        String token = request.getHeader("token");

        try {
            Map map = JwtUtil.parseToken(token);
            Object id = map.get("id");
            log.info("解析通过");
            return true;
        } catch (Exception e) {
           /*
           解析失败设置 401 状态码
            */
            response.setStatus(401);
            log.info("权限不足请检查 Token");
            return false;
        }
    }
}
