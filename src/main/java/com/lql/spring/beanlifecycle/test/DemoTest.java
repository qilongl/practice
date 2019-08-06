package com.lql.spring.beanlifecycle.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by StrangeDragon on 2019/8/2 16:43
 * 以下方法执行顺序
 * 初始化：@PostConstruct、 initMethod
 * 容器关闭：@PreDestroy、 destroyMethod （如果没有指定destroyMethod方法，默认会执行@Bean标注方法返还的类中的close方法【存在则默认执行】）
 **/
public class DemoTest {

    @PostConstruct
    public void postConstruct() {
        System.out.println("执行PostConstruct注解标注的方法");
    }

    @PreDestroy
    public void predestory() {
        System.out.println("执行preDestroy注解标注的方法");
    }

    public void init() {
        System.out.println("执行init方法");
    }

    public void destory() {
        System.out.println("执行destory方法");
    }

    public void close() {
        System.out.println("执行close方法");
    }
}
