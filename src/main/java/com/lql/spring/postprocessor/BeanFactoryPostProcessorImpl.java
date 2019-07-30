package com.lql.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by StrangeDragon on 2019/7/18 15:00
 * // 【这里需要知道 BeanFactoryPostProcessor 这个知识点，Bean 如果实现了此接口，
 * // 那么在容器初始化以后，Spring 会负责调用里面的 postProcessBeanFactory 方法。】
 * <p>
 * spring 容器加载的时候先加载 BeanFactoryPostProcessor 的实现类，BeanFactoryPostProcessor 各个实现类的 postProcessBeanFactory(factory) 方法
 * 然后再去 --->注册 BeanPostProcessor 的实现类(到这里 Bean 还没初始化)
 **/
public class BeanFactoryPostProcessorImpl implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
