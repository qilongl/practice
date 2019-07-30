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
 * 实现 MethodInterceptor 接口
 **/
public class CGlibAgent implements MethodInterceptor {
    private Object proxy;

    public Object getInstance(Object proxy) {
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.proxy.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("<<<<<<<<<<<before");
        Object ret = methodProxy.invokeSuper(o, objects);
        System.out.println("<<<<<<<<<<<after");
        return ret;
    }

    public static void main(String[] args) {
        CGlibAgent cGlibAgent = new CGlibAgent();
        Apple apple = (Apple) cGlibAgent.getInstance(new Apple());
        apple.show();
    }
}
