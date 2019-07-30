package com.lql.designmode.behaviourtype.visitor;

/**
 * Created by StrangeDragon on 2019/5/16 10:34
 **/
public interface Element {
    void accept(Visitor visitor);
}
