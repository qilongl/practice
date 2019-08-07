package com.lql.designpattern.behaviour.statue.test;

/**
 * Created by StrangeDragon on 2019/8/7 14:01
 * 状态模式
 * 通过改变一个对象的内部属性对象的状态来改变对象的行为
 * 简单来讲就是通过改变对象的状态来改变对象的行为
 **/
public class Main {
    public static void main(String[] args) {
        Statue onStatue = new OnStatue();
        Statue offStatue = new OffStatue();
        Context context = new Context(onStatue);
        context.effect();

        context.setStatue(offStatue);
        context.effect();
    }
}
