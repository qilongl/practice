package com.lql.designmode.structuraltype.bridge;

import org.apache.poi.ss.formula.functions.T;

/**
 * Created by StrangeDragon on 2019/5/16 14:34
 **/
public abstract class RemoteControl {
    protected TV tv;

    public RemoteControl(TV tv) {
        this.tv = tv;
    }

    abstract void on();

    abstract void off();

    abstract void changeChannel();
}
