package xyz.ccola.service.impl;

import com.aliyun.vod.upload.common.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.User;
import xyz.ccola.mapper.UserMapper;
import xyz.ccola.service.UserService;

/**
 * @ Name: UserServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/19 17:27
 * @ Description: UserServiceImpl
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {

        /*
        将 password 加密后查询
         */
        String passwordWithMd5 = MD5Util.md5(password);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>();
        wrapper.eq(User::getUsername,username);
        wrapper.eq(User::getPassword,passwordWithMd5);

        return userMapper.selectOne(wrapper);
    }
}
