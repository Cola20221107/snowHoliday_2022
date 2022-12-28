import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.ccola.bean.Book;
import xyz.ccola.bean.Friend;
import xyz.ccola.bean.Phone;
import xyz.ccola.bean.User;

/**
 * @ Name: InstantiatingBeanTest
 * @ Author: Cola
 * @ Time: 2022/12/28 19:15
 * @ Description: InstantiatingBeanTest
 */
public class InstantiatingBeanTest {

    /**
     * 测试 通过构造函数的方法实例化 Bean
     */
    @Test
    public void testConstructInstantiatingBean(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Book bookBean = context.getBean("bookBean", Book.class);
        System.out.println(bookBean);
    }

    /**
     * 测试 通过静态工厂的方法实例化 Bean
     */
    @Test
    public void testStaticFactoryMethodInstantiatingBean(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Friend friendBean = context.getBean("friendBean", Friend.class);
        /*
        通过 getType() 查看运行时 Bean 的类型
         */
        System.out.println("该 Bean 的类型 Type 为："+context.getType("friendBean"));
        System.out.println(friendBean);
    }

    /**
     * 测试 通过实例化工厂的方法实例化 Bean
     */
    @Test
    public void testInstanceFactoryMethodInstantiatingBean(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        User userBean = context.getBean("userBean", User.class);
        Phone phoneBean = context.getBean("phoneBean", Phone.class);
        System.out.println(userBean);
        System.out.println(phoneBean);
    }


}
