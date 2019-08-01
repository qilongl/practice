package com.lql.spring.beanlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by StrangeDragon on 2019/8/1 10:01
 * bean生命周期
 * 参考 https://www.cnblogs.com/zrtqsk/p/3735273.html
 **/
public class BeanLifeCycle {
    public static void main(String[] args) {
        System.out.println("现在开始初始化容器");
        Class[] classes = new Class[]{Config.class, MyBeanFactoryPostProcessor.class, MyBeanPostProcessor.class, MyInstantiationAwareBeanPostProcessor.class};
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(classes);
        System.out.println("容器初始化成功");

        Bean bean = context.getBean(Bean.class);
        System.out.println(bean);

        System.out.println("现在开始关闭容器");
        context.close();
    }


}
