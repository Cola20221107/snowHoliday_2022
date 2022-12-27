package xyz.ccola.dao;

import xyz.ccola.domain.Book;

import java.util.List;

/**
 * @ Name: BookDao
 * @ Author: Cola
 * @ Time: 2022/12/20 13:47
 * @ Description: BookDao
 */
public interface BookDao {
    /**
     * 查询全部 Book 对象
     * @return Book 集
     */
    List<Book> findAll();
}
