package team.sgj.test;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import team.sgj.domain.User;
import team.sgj.service.UserService;

public class SpringTest {
    @Test
    public void test01() {
        //拿到容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //获取bean
        UserService userService = context.getBean("userService", UserService.class);
        //调用方法
        User user = userService.selectByPrimaryKey(1);
        System.out.println(user);
    }
}
