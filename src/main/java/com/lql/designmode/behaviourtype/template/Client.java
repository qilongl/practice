package com.lql.designmode.behaviourtype.template;

/**
 * Created by StrangeDragon on 2019/5/16 10:04
 **/
public class Client {
    public static void main(String[] args) {
        BaseTemplate coffer = new Coffer();
        coffer.start();
        BaseTemplate tea = new Tea();
        tea.start();
    }
}
