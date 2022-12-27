package xyz.ccola.dao.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.ccola.dao.FriendDao;
import xyz.ccola.domain.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Name: FriendDaoImpl
 * @ Author: Cola
 * @ Time: 2022/12/20 14:27
 * @ Description: FriendDaoImpl
 */
@Data
@AllArgsConstructor
public class FriendDaoImpl implements FriendDao {
    @Override
    public List<Friend> findAll() {
        /*
        模拟从数据库中查询对象
         */
        Friend friend01 = new Friend(1L,"HF",1);
        Friend friend02 = new Friend(1L,"HY",2);
        Friend friend03 = new Friend(1L,"Q",2);
        Friend friend04 = new Friend(1L,"WSL",1);
        Friend friend05 = new Friend(1L,"Z",2);

        List<Friend> friendList = new ArrayList<>();
        friendList.add(friend01);
        friendList.add(friend02);
        friendList.add(friend03);
        friendList.add(friend04);
        friendList.add(friend05);
        return friendList;
    }
}
