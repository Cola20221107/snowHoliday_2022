import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import xyz.ccola.controller.BookController;
import xyz.ccola.controller.FriendController;
import xyz.ccola.domain.Book;
import xyz.ccola.domain.Friend;
import xyz.ccola.util.ApplicationContextUtil;

import java.util.List;

/**
 * @ Name: FriendControllerTest
 * @ Author: Cola
 * @ Time: 2022/12/20 14:42
 * @ Description: FriendControllerTest
 */
public class FriendControllerTest {

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
        ApplicationContext context = ApplicationContextUtil.getApplicationContext();
        FriendController friendController = context.getBean("friendController", FriendController.class);
        List<Friend> friendList = friendController.findAll();
        for (Friend friend : friendList) {
            System.out.println(friend);
        }
    }
}
