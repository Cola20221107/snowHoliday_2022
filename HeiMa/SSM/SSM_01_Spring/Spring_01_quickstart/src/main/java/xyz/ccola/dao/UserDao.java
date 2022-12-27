package xyz.ccola.dao;

import xyz.ccola.domain.User;

import java.util.List;

/**
 * @ Name: UserDao
 * @ Author: Cola
 * @ Time: 2022/12/20 13:22
 * @ Description: UserDao
 */
public interface UserDao {
    /**
     * 查询全部 User 对象
     * @return User 集
     */
    List<User> findAll();
}
