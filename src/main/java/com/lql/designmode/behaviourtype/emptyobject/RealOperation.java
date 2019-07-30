package com.lql.designmode.behaviourtype.emptyobject;

/**
 * Created by StrangeDragon on 2019/5/16 13:52
 **/
public class RealOperation extends AbstractOperation {
    @Override
    void request() {
        System.out.println("real request!");
    }
}
