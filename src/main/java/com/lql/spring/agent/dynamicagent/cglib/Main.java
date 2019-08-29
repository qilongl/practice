package com.lql.spring.agent.dynamicagent.cglib;

/**
 * Created by StrangeDragon on 2019/8/8 14:17
 * Cglib动态代理
 **/
public class Main {
    public static void main(String[] args) {
        CGlibAgent cGlibAgent = new CGlibAgent();
        Apple apple = (Apple) cGlibAgent.getInstance(new Apple());
        apple.show();
    }
}
