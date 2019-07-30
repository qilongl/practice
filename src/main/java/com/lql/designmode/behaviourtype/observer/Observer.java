package com.lql.designmode.behaviourtype.observer;

/**
 * Created by StrangeDragon on 2019/5/16 9:23
 **/
public interface Observer {
    void insertRegister(Register register);

    void removeRegister(Register register);

    void notification();
}
