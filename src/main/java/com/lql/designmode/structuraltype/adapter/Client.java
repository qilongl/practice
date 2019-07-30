package com.lql.designmode.structuraltype.adapter;

/**
 * Created by StrangeDragon on 2019/5/16 14:20
 **/
public class Client {
    public static void main(String[] args) {
        RealDuck realDuck = new RealDuck();
        Chick chick = new DuckAdapter(realDuck);
        chick.gugu();
    }
}
