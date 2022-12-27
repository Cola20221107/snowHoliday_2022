package xyz.ccola.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ccola.domain.User;
import xyz.ccola.mapper.UserMapper;
import xyz.ccola.service.UserService;

/**
 * @ Name: UserServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/27 19:53
 * @ Description: UserServiceImpl
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
