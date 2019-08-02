package com.lql.designpattern.templatepattern.test1;

/**
 * Created by StrangeDragon on 2019/8/1 16:51
 * 红烧肉
 **/
public class Bouilli extends CookDinner {
    @Override
    protected void prepareFood() {
        System.out.println("五花肉500g+ 板栗300g+ 红糖50g+...");
    }

    @Override
    protected void cook() {
        System.out.println("大火烧一个小时");
        System.out.println("小火焖上3个小时");
    }

    @Override
    protected void eat() {
        System.out.println("尼玛，别看做的时间久，吃起来贼爽");
    }
}
