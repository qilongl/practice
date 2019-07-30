package com.lql.designmode.behaviourtype.template;

/**
 * Created by StrangeDragon on 2019/5/16 9:58
 **/
public class Tea extends BaseTemplate {

    @Override
    public void drinkWater() {
        System.out.println("drink tea!");
    }

    @Override
    void step1() {
        System.out.println("make tea!");
    }

    @Override
    void step2() {
        System.out.println("wait tea!");
    }
}
