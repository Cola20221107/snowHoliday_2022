import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xyz.ccola.controller.BookController;
import xyz.ccola.controller.UserController;
import xyz.ccola.domain.Book;
import xyz.ccola.domain.User;

import java.util.List;

/**
 * @ Name: BookControllerTest
 * @ Author: Cola
 * @ Time: 2022/12/20 14:00
 * @ Description: BookControllerTest
 */
public class BookControllerTest {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * 模拟前端访问 Controller
     * Method: findAll()
     *
     */
    @Test
    public void testFindAll() throws Exception {
        BookController bookController = new BookController();
        List<Book> bookList = bookController.findAll();
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

}
