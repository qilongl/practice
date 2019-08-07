package com.lql.designpattern.structural.adapter.test1;

import com.lql.designmode.behaviourtype.mediator.D;

/**
 * Created by StrangeDragon on 2019/8/7 15:02
 * 适配器模式
 *
 **/
public class Main {
    public static void main(String[] args) {
        YellowDuck duck=new YellowDuck();
        Chicken chicken=new DuckAdapter(duck);
        chicken.say();
    }
}
