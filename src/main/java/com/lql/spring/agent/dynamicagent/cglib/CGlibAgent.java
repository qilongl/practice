package com.lql.spring.agent.dynamicagent.cglib;

import com.lql.designmode.behaviourtype.mediator.A;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by StrangeDragon on 2019/7/11 13:40
 * 基于CGlib的动态代理
 * 适用于代理的类是没有实现接口的类
 * 实现 MethodInterceptor 接口
 **/
public class CGlibAgent implements MethodInterceptor {
    private Object proxy;

    /**
     * 通过 CGLIB 动态代理获取被代理的对象
     *
     * @param proxy 被代理的对象
     * @return
     */
    public Object getInstance(Object proxy) {
        this.proxy = proxy;
        // 通过 CGLIB 动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类(也就是我们所要代理的类)
        enhancer.setSuperclass(this.proxy.getClass());
        // 设置enhancer的回调对象（即实现了 MethodInterceptor 接口的对象）
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * @param o           表示增强的对象，即实现这个接口类的一个对象
     * @param method      表示要被拦截的方法
     * @param objects     表示要被拦截方法的参数
     * @param methodProxy 表示要触发父类的方法对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("<<<<<<<<<<<before");
        Object ret = methodProxy.invokeSuper(o, objects);
        System.out.println("<<<<<<<<<<<after");
        return ret;
    }
}
