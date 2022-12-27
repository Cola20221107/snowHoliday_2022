package xyz.ccola.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Name: Friend
 * @ Author: Cola
 * @ Time: 2022/12/20 14:23
 * @ Description: Friend
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    private Long id;
    private String friendname;
    private Integer sex;
}
