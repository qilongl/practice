package com.lql.designmode.behaviourtype.emptyobject;

/**
 * Created by StrangeDragon on 2019/5/16 13:54
 **/
public class Client {
    public static void main(String[] args) {
        AbstractOperation abstractOperation = func(0);
        abstractOperation.request();
    }

    static AbstractOperation func(int param) {
        if (param < 0) {
            return new NullOperation();
        }
        return new RealOperation();
    }
}
