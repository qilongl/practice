package com.lql.designpattern.structural.bridge.test1;

/**
 * Created by StrangeDragon on 2019/8/7 15:34
 * 桥接模式
 * 如果之前你看了策略模式的demo，可以看出来桥接模式是在策略模式基础上拓展的
 * 桥接模式就是为了是代码结构更加解耦和易扩展
 **/
public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5, new RedPen());
        circle.draw();

        Shape square=new Square(10,new YellowPen());
        square.draw();

        Shape retangle=new Rectangle(5,9,new BluePen());
        retangle.draw();
    }
}
