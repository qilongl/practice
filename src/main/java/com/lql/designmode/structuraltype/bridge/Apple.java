package com.lql.designmode.structuraltype.bridge;

/**
 * Created by StrangeDragon on 2019/5/16 14:33
 **/
public class Apple implements TV {
    @Override
    public void on() {
        System.out.println("Apple on");
    }

    @Override
    public void off() {
        System.out.println("Apple off");
    }

    @Override
    public void changeChannel() {
        System.out.println("change channel");
    }
}
