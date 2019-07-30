package com.lql.designmode.behaviourtype.observer;

/**
 * Created by StrangeDragon on 2019/5/16 9:36
 **/
public class Client {
    public static void main(String[] args) {
        ConcreteObserver observer = new ConcreteObserver();
        Register1 register1 = new Register1(observer);
        Register2 register2 = new Register2(observer);
        observer.sendNotice("主题下发通知");
    }
}
