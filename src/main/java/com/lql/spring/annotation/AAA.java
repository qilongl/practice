package com.lql.spring.annotation;

/**
 * Created by StrangeDragon on 2019/7/9 13:46
 **/
public class AAA {
    private BBB bbb;

    public AAA(BBB bbb) {
        System.out.println("AAA 有参构造初始化");
        this.bbb = bbb;
    }

    public void  say(){
        bbb.say();
    }

}
