package com.lql.designmode.structuraltype.bridge;

/**
 * Created by StrangeDragon on 2019/5/16 14:39
 **/
public class Client {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Sony sony = new Sony();
        RemoteControl1 remoteControl1 = new RemoteControl1(apple);
        remoteControl1.on();
        remoteControl1.changeChannel();
        remoteControl1.off();

        RemoteControl2 remoteControl2 = new RemoteControl2(sony);
        remoteControl2.on();
        remoteControl2.changeChannel();
        remoteControl2.off();

    }
}
