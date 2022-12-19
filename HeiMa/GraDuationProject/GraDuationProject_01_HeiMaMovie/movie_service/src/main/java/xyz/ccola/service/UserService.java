package xyz.ccola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.User;

/**
 * @ Name: UserService
 * @ Author: Cola
 * @ Time: 2022/12/19 17:21
 * @ Description: UserService
 */
@Service
public interface UserService extends IService<User> {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    User login(String username, String password);
}
