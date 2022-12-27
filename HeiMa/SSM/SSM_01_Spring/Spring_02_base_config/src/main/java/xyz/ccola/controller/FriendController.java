package xyz.ccola.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import xyz.ccola.domain.Friend;
import xyz.ccola.service.FriendService;
import xyz.ccola.util.ApplicationContextUtil;

import java.util.List;

/**
 * @ Name: FriendController
 * @ Author: Cola
 * @ Time: 2022/12/20 14:31
 * @ Description: FriendController
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendController {

    private FriendService friendService;
    /**
     * 模拟前端请求
     * @return Friend 集
     */
    public List<Friend> findAll(){
        return friendService.findAll();
    }
}
