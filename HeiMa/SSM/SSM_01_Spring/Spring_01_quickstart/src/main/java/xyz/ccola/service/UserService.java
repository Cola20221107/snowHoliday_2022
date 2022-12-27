package xyz.ccola.service;

import xyz.ccola.domain.User;

import java.util.List;

/**
 * @ Name: UserService
 * @ Author: Cola
 * @ Time: 2022/12/20 13:22
 * @ Description: UserService
 */
public interface UserService {
    /**
     * 查询全部 User 对象
     * @return User 集
     */
    List<User> findAll();

}
