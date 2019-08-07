package com.lql.designpattern.behaviour.statue.test;

/**
 * Created by StrangeDragon on 2019/8/7 14:01
 **/
public class OnStatue implements Statue {
    @Override
    public void handle() {
        System.out.println("打开灯");
    }
}
