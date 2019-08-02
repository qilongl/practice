package com.lql.designpattern.templatepattern.test1;

/**
 * Created by StrangeDragon on 2019/8/1 16:48
 * 面条
 **/
public class Noodles extends CookDinner {
    @Override
    protected void prepareFood() {
        System.out.println("买上一把面条");
    }

    @Override
    protected void cook()  {
        System.out.println("把水烧开，加入面条和料包");
        System.out.println("我擦，煮好了");

    }

    @Override
    protected void eat() {
        System.out.println("嗖嗖，吃完了，搞定");
    }
}
