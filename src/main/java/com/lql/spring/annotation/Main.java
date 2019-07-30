package com.lql.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by StrangeDragon on 2019/7/9 14:25
 **/
@Configuration
public class Main {

    @Bean
    public HelloWorld helloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setMessage("你好。。。。。。。。。");
        return helloWorld;
    }

    @Bean
    public CStartEventHandler cStartEventHandler() {
        return new CStartEventHandler();
    }

    @Bean
    public CStopEventHandler cStopEventHandler() {
        return new CStopEventHandler();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        ctx.start();
        HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
        helloWorld.getMessage();
        ctx.stop();
    }
}
