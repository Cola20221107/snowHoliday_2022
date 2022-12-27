package xyz.ccola.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.ccola.domain.User;
import xyz.ccola.service.UserService;
import xyz.ccola.service.impl.UserServiceImpl;

import java.util.List;

/**
 * @ Name: UserController
 * @ Author: Cola
 * @ Time: 2022/12/20 13:22
 * @ Description: UserController
 */
@Data
@AllArgsConstructor
public class UserController {

    /**
     * 模拟前端请求
     * @return User 集
     */
   public List <User> findAll(){
        /*
        调用 Service 层
         */
        UserService userService = new UserServiceImpl();

        return    userService.findAll();
    }
}
