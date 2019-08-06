package com.lql.spring.beanlifecycle.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by StrangeDragon on 2019/8/2 16:51
 **/
@Configuration
public class Config {

    @Bean(initMethod = "init", destroyMethod = "destory")
    public DemoTest demoTest() {
        return new DemoTest();
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        DemoTest demoTest = context.getBean(DemoTest.class);
        context.close();

    }
}
