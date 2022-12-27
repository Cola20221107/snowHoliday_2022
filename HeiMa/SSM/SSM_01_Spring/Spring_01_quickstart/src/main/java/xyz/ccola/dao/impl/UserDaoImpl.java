package xyz.ccola.dao.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.ccola.dao.UserDao;
import xyz.ccola.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Name: UserDaoImpl
 * @ Author: Cola
 * @ Time: 2022/12/20 13:24
 * @ Description: UserDaoImpl
 */
@Data
@AllArgsConstructor
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        /*
        模拟数据库查询数据
         */
        User user01 = new User(1L,"Cola",21);
        User user02 = new User(2L,"XiaoEr",21);
        User user03 = new User(3L,"Test",22);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user01);
        userList.add(user02);
        userList.add(user03);

        return userList;
    }
}
