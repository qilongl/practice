package com.lql.designpattern.structural.adapter.test1;

/**
 * Created by StrangeDragon on 2019/8/7 14:58
 * 适配器，将鸭子适配成鸡
 * 有没有发现，跟代理模式还是有点像的
 * 代理模式：为了增强原方法，比如在方法前后做点事情
 * 适配器模式：就是为了适配，用鸭子的say() 来作为鸡的 say()，将鸭子当鸡用
 **/
public class DuckAdapter implements Chicken {
    private Duck duck;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
    }

    @Override
    public void say() {
        System.out.println("我是小黄鸭适配器，用来将小黄鸭适配成小鸡的");
        duck.say();
    }

}
