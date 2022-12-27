import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import xyz.ccola.controller.UserController;
import xyz.ccola.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Name: UserControllerTest
 * @ Author: Cola
 * @ Time: 2022/12/20 13:37
 * @ Description: UserControllerTest
 */

public class UserControllerTest {

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
        UserController userController = new UserController();
        List<User> userList = userController.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 测试 Consumer<T> 实现 Lambda
     */
    @Test
    public void testEach(){
        UserController userController = new UserController();
        List<User> userList = userController.findAll();
        /*
        原始
         */
        userList.forEach((item)->{
            System.out.println(item.getUsername());
            System.out.println(item);
        });
        /*
        静态方法
         */
        userList.forEach(System.out::println);
    }

    @Test
    public void testFor(){
        for (int i = 0; i <Integer.MAX_VALUE; i++) {
          List<Integer> myList =   new ArrayList<>();
                myList.add(i);
                myList.forEach((item)->{System.out.println(item);});
        }
    }


}

