package xyz.ccola.dao.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.ccola.dao.BookDao;
import xyz.ccola.domain.Book;
import xyz.ccola.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Name: BookDaoImpl
 * @ Author: Cola
 * @ Time: 2022/12/20 13:47
 * @ Description: BookDaoImpl
 */
@Data
@AllArgsConstructor
public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> findAll() {
        /*
        模拟数据库查询数据
         */
        Book book01 = new Book(1L,"Java",1);
        Book book02 = new Book(2L,"C++",2);
        Book book03 = new Book(3L,"Vue",3);
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book01);
        bookList.add(book02);
        bookList.add(book03);

        return bookList;
    }
}
