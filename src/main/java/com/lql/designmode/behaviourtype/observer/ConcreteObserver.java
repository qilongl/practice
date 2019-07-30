package com.lql.designmode.behaviourtype.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StrangeDragon on 2019/5/16 9:27
 **/
public class ConcreteObserver implements Observer {
    private List<Register> registers;


    private Object o;

    public ConcreteObserver() {
        registers = new ArrayList<>();
    }

    public void sendNotice(Object o) {
        this.o = o;
        notification();
    }

    @Override
    public void insertRegister(Register register) {
        registers.add(register);
    }

    @Override
    public void removeRegister(Register register) {
        if (registers.indexOf(register) > 0) {
            registers.remove(register);
        }
    }

    @Override
    public void notification() {
        for (Register register : registers) {
            register.update(o);
        }

    }
}
