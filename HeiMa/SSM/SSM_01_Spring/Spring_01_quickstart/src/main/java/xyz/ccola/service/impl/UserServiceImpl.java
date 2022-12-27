package xyz.ccola.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.ccola.dao.UserDao;
import xyz.ccola.dao.impl.UserDaoImpl;
import xyz.ccola.domain.User;
import xyz.ccola.service.UserService;

import java.util.List;

/**
 * @ Name: UserServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/20 13:23
 * @ Description: UserServiceImpl
 */
@Data
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    /*
    调用 UserDao
     */
    @Override
    public List<User> findAll() {
        UserDao userDao = new UserDaoImpl();
        return userDao.findAll();
    }
}
