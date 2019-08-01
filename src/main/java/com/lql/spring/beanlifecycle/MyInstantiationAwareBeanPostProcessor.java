package com.lql.spring.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyDescriptor;

/**
 * Created by StrangeDragon on 2019/8/1 9:49
 **/
@Configuration
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    /**
     * 接口方法、初始化Bean之后调用。
     * 此方法调用完成后，容器初始化完成，程序执行，业务逻辑调用各个bean
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法");
        return super.postProcessAfterInitialization(bean, beanName);
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}
