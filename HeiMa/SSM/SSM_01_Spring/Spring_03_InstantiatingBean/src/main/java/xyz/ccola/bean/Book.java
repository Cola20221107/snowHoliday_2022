package xyz.ccola.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Name: Book
 * @ Author: Cola
 * @ Time: 2022/12/28 18:59
 * @ Description: Book
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String bookname;
    private Integer type;
}