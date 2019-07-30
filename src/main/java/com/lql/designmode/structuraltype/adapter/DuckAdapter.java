package com.lql.designmode.structuraltype.adapter;

/**
 * Created by StrangeDragon on 2019/5/16 14:09
 **/
public class DuckAdapter implements Chick {
    private Duck duck;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
    }

    @Override
    public void gugu() {
        duck.duck();
    }
}
