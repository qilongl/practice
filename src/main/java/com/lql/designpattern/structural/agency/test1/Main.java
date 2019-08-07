package com.lql.designpattern.structural.agency.test1;

/**
 * Created by StrangeDragon on 2019/8/7 14:30
 * 代理模式
 * 动态代理：
 * 1、基于接口的代理   jdk 动态代理
 * 2、基于类的代理     Cglib 动态代理
 * Spring中的Aop
 **/
public class Main {
    public static void main(String[] args) {
        IService serviceProxy=new ServiceProxy();
        serviceProxy.say();
    }
}
