package com.lql.designmode.behaviourtype.strategy;

/**
 * Created by StrangeDragon on 2019/5/16 9:50
 **/
public class Behavior1 implements Strategy {
    @Override
    public void behavior() {
        System.out.println(this.getClass().getSimpleName());
    }
}
