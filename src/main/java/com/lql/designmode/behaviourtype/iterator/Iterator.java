package com.lql.designmode.behaviourtype.iterator;

/**
 * Created by StrangeDragon on 2019/5/15 15:48
 **/
public interface Iterator<Item> {
    Item next();

    boolean hasNext();
}
