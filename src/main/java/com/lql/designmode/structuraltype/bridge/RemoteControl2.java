package com.lql.designmode.structuraltype.bridge;

/**
 * Created by StrangeDragon on 2019/5/16 14:37
 **/
public class RemoteControl2 extends RemoteControl {
    public RemoteControl2(TV tv) {
        super(tv);
    }

    @Override
    void on() {
        System.out.println("遥控2打开");
    }

    @Override
    void off() {
        System.out.println("遥控2关闭");
    }

    @Override
    void changeChannel() {
        System.out.println("遥控2换频道");
    }
}
