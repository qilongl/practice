package com.lql.spring.postprocessor;

import com.lql.db.redis.util.RedisClusterUtil;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by StrangeDragon on 2019/7/9 10:42
 * 注册 BeanPostProcessor 的实现类，注意看和 BeanFactoryPostProcessor 的区别
 * // 此接口两个方法: postProcessBeforeInitialization 和 postProcessAfterInitialization
 * // 两个方法分别在 Bean 初始化之前和初始化之后得到执行。
 **/
@Component
public class InitHelloWorld implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if (o instanceof RedisClusterUtil) {
//            System.err.println("RedisClusterUtil初始化之前");
        } else {
//            System.out.println("初始化之前：" + s);
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (o instanceof RedisClusterUtil) {
//            System.err.println("RedisClusterUtil初始化之后");
        } else {
//            System.out.println("初始化之后：" + s);
        }
        return o;
    }
}
