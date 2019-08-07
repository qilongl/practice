package com.lql.designpattern.behaviour.templatepattern.test1;

/**
 * Created by StrangeDragon on 2019/8/1 16:53
 * 模拟做饭
 **/
public class Main {
    public static void main(String[] args) {
        CookDinner noodles = new Noodles();
        CookDinner bouilli = new Bouilli();
        noodles.templateFunction();
        System.out.println("-----------------");
        bouilli.templateFunction();
    }
}
