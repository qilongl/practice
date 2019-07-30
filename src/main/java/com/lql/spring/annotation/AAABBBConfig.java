package com.lql.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by StrangeDragon on 2019/7/9 13:49
 **/
@Configuration
public class AAABBBConfig {

    @Bean
    public AAA aaa() {
        return new AAA(bbb());
    }

    @Bean
    public BBB bbb() {
        return new BBB();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AAABBBConfig.class);
        AAA aaa = ctx.getBean(AAA.class);
        AAA aaa1 = ctx.getBean(AAA.class);
        System.out.println("是否单例：" + (aaa == aaa1));

        BBB bbb = ctx.getBean(BBB.class);
        BBB bbb1 = ctx.getBean(BBB.class);
        System.out.println("是否单例：" + (bbb == bbb1));
    }
}
