package xyz.ccola.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import xyz.ccola.dao.BookDao;
import xyz.ccola.domain.Book;
import xyz.ccola.service.BookService;
import xyz.ccola.util.ApplicationContextUtil;

import java.util.List;

/**
 * @ Name: BookServiceImpl
 * @ Author: Cola
 * @ Time: 2022/12/20 13:50
 * @ Description: BookServiceImpl
 */
@Data
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    @Override
    public List<Book> findAll() {
        /*
        通过 IOC 容器获取 bookDao
         */
        ApplicationContext context = ApplicationContextUtil.getApplicationContext();
        BookDao bookDao = context.getBean("bookDao", BookDao.class);
        return bookDao.findAll();
    }
}
