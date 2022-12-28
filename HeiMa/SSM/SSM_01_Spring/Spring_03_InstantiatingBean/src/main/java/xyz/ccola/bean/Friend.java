package xyz.ccola.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Name: Friend
 * @ Author: Cola
 * @ Time: 2022/12/28 19:00
 * @ Description: Friend
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {

    private static Friend friend = new Friend();

    private Long id;
    private String friendname;
    private Integer sex;

    /**
     * 用于返回 friend 对象的静态工厂
     * @return friend 对象
     */
    public static Friend friendFactory(){
        System.out.println("在这里可以进行自定义操作 [Friend]");
        return friend;
    }
}
