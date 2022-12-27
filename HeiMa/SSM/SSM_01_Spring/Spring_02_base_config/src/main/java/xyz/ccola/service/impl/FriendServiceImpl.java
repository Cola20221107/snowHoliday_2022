package xyz.ccola.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.ccola.dao.FriendDao;
import xyz.ccola.domain.Friend;
import xyz.ccola.service.FriendService;

import java.util.List;

/**
 * @ Name: FriendServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/20 14:30
 * @ Description: FriendServiceImpl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendServiceImpl implements FriendService {

    /*
    通过 IOC容器 DI 自动装配
     */
    private FriendDao friendDao;
    @Override
    public List<Friend> findAll() {
        return friendDao.findAll();
    }
}
