package com.lql.designmode.behaviourtype.mediator;

/**
 * Created by StrangeDragon on 2019/5/15 17:03
 **/
public class B implements Colleague {
    @Override
    public void  onEvent(Mediator mediator) {
        mediator.doEvent(this.getClass().getSimpleName());
    }

    public void doB() {
        System.out.println("doB()");
    }
}
