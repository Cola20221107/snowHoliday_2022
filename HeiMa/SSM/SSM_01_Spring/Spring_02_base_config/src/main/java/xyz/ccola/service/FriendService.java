package xyz.ccola.service;

import xyz.ccola.domain.Friend;

import java.util.List;

/**
 * @ Name: FriendService
 * @ Author: Cola
 * @ Time: 2022/12/20 14:29
 * @ Description: FriendService
 */
public interface FriendService {

    /**
     * 查找全部 Friend
     * @return Friend 集
     */
    List<Friend> findAll();
}
