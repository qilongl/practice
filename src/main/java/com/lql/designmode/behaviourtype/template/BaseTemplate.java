package com.lql.designmode.behaviourtype.template;

/**
 * Created by StrangeDragon on 2019/5/16 9:57
 **/
public abstract class BaseTemplate {

    final void start() {
        addWater();
        step1();
        step2();
        drinkWater();
    }

    void addWater() {
        System.out.println("add water!");
    }

    void drinkWater() {
        System.out.println("drink water!");
    }

    abstract void step1();

    abstract void step2();
}
