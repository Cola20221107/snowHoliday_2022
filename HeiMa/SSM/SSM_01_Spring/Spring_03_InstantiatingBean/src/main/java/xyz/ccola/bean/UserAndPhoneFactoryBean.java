package xyz.ccola.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Name: UserAndPhoneFactoryBean
 * @ Author: Cola
 * @ Time: 2022/12/28 19:09
 * @ Description: UserAndPhoneFactoryBean
 */
@Data
@NoArgsConstructor
public class UserAndPhoneFactoryBean {
    private static User user = new User();
    private static Phone phone = new Phone();

    /**
     * 用于返回 user 对象的工厂
     * @return user 对象
     */
    public User userFactory(){
        System.out.println("在这里可以进行自定义操作 [User]");
        return user;
    }

    /**
     * 用于返回 phone 对象的工厂
     * @return phone 对象
     */
    public  Phone phoneFactory(){
        System.out.println("在这里可以进行自定义操作 [Phone]");
        return phone;
    }
}
