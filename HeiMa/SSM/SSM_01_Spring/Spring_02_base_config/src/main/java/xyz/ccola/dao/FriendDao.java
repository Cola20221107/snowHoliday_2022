package xyz.ccola.dao;

import xyz.ccola.domain.Friend;

import java.util.List;

/**
 * @ Name: FriendDao
 * @ Author: Cola
 * @ Time: 2022/12/20 14:26
 * @ Description: FriendDao
 */
public interface FriendDao {

    /**
     * 查找全部 Friend
     * @return Friend 集
     */
    List<Friend> findAll();
}
