package com.lql.designpattern.behaviour.strategy.test1;

/**
 * Created by StrangeDragon on 2019/8/6 17:35
 * 策略模式
 * 简单理解就是将程序中变化的部分封装起来，可以替换。
 * 也就是说策略模式就是将可变的部分独立于客户端，客户端来决定用哪一种策略，这个策略到底是什么，没错，就是可变的东西（可以理解为模板）
 **/
public class Main {
    public static void main(String[] args) {
        Picture picture = new Picture(new RedPen());
        picture.draw(10, 1, 1);
    }
}
