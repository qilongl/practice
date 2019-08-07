package com.lql.designpattern.structural.adapter.test1;

/**
 * Created by StrangeDragon on 2019/8/7 14:57
 **/
public class YellowDuck implements Duck {
    @Override
    public void say() {
        System.out.println("我是小黄鸭");
    }
}
