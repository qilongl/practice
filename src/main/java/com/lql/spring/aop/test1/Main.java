package com.lql.spring.aop.test1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by StrangeDragon on 2019/7/24 10:09
 **/
public class Main {
    /**
     * advice 通知的执行先后顺序：@Before、@Around、@After、@Around、@AfterReturning
     * @param args
     */
    public static void main(String[] args) {
        Class[] classes = new Class[]{UserServiceImpl.class, LogArgsAspect.class, LogResultAspect.class, LogAroundAspect.class,LogAfterAspect.class,LogAfterThrowAspect.class};
        ApplicationContext context = new AnnotationConfigApplicationContext(classes);
        UserService userService = context.getBean(UserService.class);
        User user = new User();
        user.setName("123");
        user.setAge(123);
//        userService.CreateUser(user);
        userService.getUserList();
    }
}
