package com.lql.spring.beanlifecycle;

import org.springframework.context.annotation.Configuration;

/**
 * Created by StrangeDragon on 2019/8/1 10:15
 **/
@Configuration
public class BeanConfig {
    @org.springframework.context.annotation.Bean(initMethod = "init", destroyMethod = "destory")
    public Bean bean() {
        return new Bean();
    }
}
