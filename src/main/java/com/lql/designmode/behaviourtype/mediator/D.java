package com.lql.designmode.behaviourtype.mediator;

/**
 * Created by StrangeDragon on 2019/5/15 17:03
 **/
public class D implements Colleague {
    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent(this.getClass().getSimpleName());
    }

    public void doD() {
        System.out.println("doD()");
    }
}
