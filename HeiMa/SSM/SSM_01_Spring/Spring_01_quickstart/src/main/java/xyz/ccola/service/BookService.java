package xyz.ccola.service;

import xyz.ccola.domain.Book;

import java.util.List;

/**
 * @ Name: BookService
 * @ Author: Cola
 * @ Time: 2022/12/20 13:50
 * @ Description: BookService
 */
public interface BookService {
    /**
     * 查询全部 Book 对象
     * @return Book 集
     */
    List<Book> findAll();
}
