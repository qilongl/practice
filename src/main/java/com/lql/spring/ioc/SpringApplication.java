package com.lql.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by StrangeDragon on 2019/7/22 14:26
 * 如需启动该容器，请将该目录下的 application.xml移动到classpath下（即 resources 下面）
 **/
public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/lql/spring/ioc/application.xml");
        System.out.println("context 启动成功！");
        MessageService messageService = (MessageService) context.getBean("messageService");
        System.out.println(messageService.getMessage());
    }
}
