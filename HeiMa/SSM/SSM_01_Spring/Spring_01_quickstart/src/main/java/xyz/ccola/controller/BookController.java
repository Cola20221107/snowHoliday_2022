package xyz.ccola.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import xyz.ccola.domain.Book;
import xyz.ccola.service.BookService;
import xyz.ccola.util.ApplicationContextUtil;


import java.util.List;

/**
 * @ Name: BookController
 * @ Author: Cola
 * @ Time: 2022/12/20 13:45
 * @ Description: BookController
 */
@Data
@AllArgsConstructor
public class BookController {

    /**
     * 模拟前端请求
     * @return Book 集
     */
    public List<Book> findAll(){
        /*
        通过 IOC 容器获取 bookService 对象
         */
        ApplicationContext context = ApplicationContextUtil.getApplicationContext();
        BookService bookService = context.getBean("bookService", BookService.class);
        return bookService.findAll();
    }
}
