package com.lql.designpattern.behaviour.strategy.test1;

/**
 * Created by StrangeDragon on 2019/8/6 17:35
 * 策略模式
 **/
public class Main {
    public static void main(String[] args) {
        Picture picture = new Picture(new RedPen());
        picture.draw(10,1,1);
    }
}
