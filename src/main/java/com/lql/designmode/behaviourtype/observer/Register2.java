package com.lql.designmode.behaviourtype.observer;

/**
 * Created by StrangeDragon on 2019/5/16 9:35
 **/
public class Register2 implements Register {
    public Register2(Observer observer) {
        observer.insertRegister(this);
    }

    @Override
    public void update(Object o) {
        System.out.println(this.getClass().getSimpleName() + " ------> " + o.toString());
    }
}
