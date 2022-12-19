package xyz.ccola.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ Name: Member
 * @ Author: Cola
 * @ Time: 2022/12/19 17:16
 * @ Description: Member
 */
@TableName("tb_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Serializable {
    /*
    主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /*
    用户名
     */
    private String username;
    /*
    密码
     */
    private String password;
    /*
    手机号
     */
    private String phone;
}
