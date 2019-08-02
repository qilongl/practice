package com.lql.designpattern.observerpattern.test2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by StrangeDragon on 2019/8/2 11:08
 * 模拟事件驱动
 * Spring事件驱动模型
 * 1、事件        继承ApplicationEvent
 * 2、监听器      实现ApplicationListener<T>   T:要监听的事件类
 * 3、事件发布    通过ApplicationContext的publishEvent<T> T:事件实例
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext context= new AnnotationConfigApplicationContext(DemoListener.class,DemoPublisher.class);
        DemoPublisher demoPublisher=context.getBean(DemoPublisher.class);
        demoPublisher.publish("嘿呦外");
    }
}
