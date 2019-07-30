package com.lql.designmode.structuraltype.bridge;

/**
 * Created by StrangeDragon on 2019/5/16 14:37
 **/
public class RemoteControl1 extends RemoteControl {
    public RemoteControl1(TV tv) {
        super(tv);
    }

    @Override
    void on() {
        System.out.println("遥控1打开");
    }

    @Override
    void off() {
        System.out.println("遥控1关闭");
    }

    @Override
    void changeChannel() {
        System.out.println("遥控1换频道");
    }
}
