package xyz.ccola.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Name: Phone
 * @ Author: Cola
 * @ Time: 2022/12/28 19:09
 * @ Description: Phone
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    private Integer id;
    private String phonename;
    private String typeVersion;
}
