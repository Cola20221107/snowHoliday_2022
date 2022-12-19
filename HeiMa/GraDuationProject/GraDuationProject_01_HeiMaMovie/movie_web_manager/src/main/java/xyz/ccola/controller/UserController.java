package xyz.ccola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccola.common.JwtUtil;
import xyz.ccola.domain.User;
import xyz.ccola.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Name: UserController
 * @ Author: Cola
 * @ Time: 2022/12/19 17:38
 * @ Description: UserController
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return Map
     */
    @RequestMapping("/login")
    public Map<String, String> login(String username, String password) {

        User user = userService.login(username, password);

        Map<String, String> map = new HashMap<>();
        if (user == null) {
            map.put("code", "0");
        } else {
            HashMap<String, Object> tokenMap = new HashMap<>();
            tokenMap.put("id", user.getId());
            String token = JwtUtil.createToken(tokenMap);

            map.put("code", "1");
            map.put("token", token);
        }

        return map;
    }

    @RequestMapping("/findAll")
    List<User> findAll(){
        return  userService.list(null);
    }


}
