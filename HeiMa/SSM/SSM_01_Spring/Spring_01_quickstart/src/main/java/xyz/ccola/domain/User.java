package xyz.ccola.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Name: User
 * @ Author: Cola
 * @ Time: 2022/12/20 13:25
 * @ Description: User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private Integer age;
}
