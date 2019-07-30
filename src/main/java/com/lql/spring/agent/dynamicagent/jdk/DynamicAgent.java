package com.lql.spring.agent.dynamicagent.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by StrangeDragon on 2019/7/11 11:09
 * 基于jdk的动态代理
 * 实现 InvocationHandler 接口
 * Proxy.newProxyInstance() 方法
 **/
public class DynamicAgent {
    static class Handler implements InvocationHandler {
        private Object proxy;

        public Handler(Object proxy) {
            this.proxy = proxy;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(">>>>>>>>>>代理之前。。。");
            Object ret = method.invoke(this.proxy, args);
            System.out.println(">>>>>>>>>>代理之后。。。");
            return ret;
        }

    }

    public static Object agent(Class interfaceClazz, Object proxy) {
        return Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class[]{interfaceClazz}, new Handler(proxy));
    }

    public static void main(String[] args) {
        Fruit apple = (Fruit) DynamicAgent.agent(Fruit.class, new Apple());
        Fruit banana = (Fruit) DynamicAgent.agent(Fruit.class, new Banana());
        apple.show();
        banana.show();
    }


}
