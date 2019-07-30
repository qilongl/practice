package com.lql.designmode.behaviourtype.observer;

/**
 * Created by StrangeDragon on 2019/5/16 9:33
 **/
public class Register1 implements Register {
    public Register1(Observer observer) {
        observer.insertRegister(this);
    }

    @Override
    public void update(Object o) {
        System.out.println(this.getClass().getSimpleName() + " ------> " + o.toString());
    }
}
