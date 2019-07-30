package com.lql.spring.annotation;

import com.sun.jdi.PathSearchingVirtualMachine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by StrangeDragon on 2019/7/9 14:06
 **/
@Configuration
public class CCCConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public CCC ccc() {
        System.out.println("初始化之后执行init()方法");
        return new CCC();
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(CCCConfig.class);
        CCC ccc = ctx.getBean(CCC.class);
    }
}
