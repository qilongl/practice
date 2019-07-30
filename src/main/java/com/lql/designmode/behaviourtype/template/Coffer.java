package com.lql.designmode.behaviourtype.template;

/**
 * Created by StrangeDragon on 2019/5/16 9:58
 **/
public class Coffer extends BaseTemplate {

    @Override
    public void drinkWater() {
        System.out.println("drink coffer!");
    }

    @Override
    void step1() {
        System.out.println("mix coffer!");
    }

    @Override
    void step2() {
        System.out.println("fix coffer!");
    }
}
