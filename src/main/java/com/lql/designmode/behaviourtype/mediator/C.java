package com.lql.designmode.behaviourtype.mediator;

/**
 * Created by StrangeDragon on 2019/5/15 17:03
 **/
public class C implements Colleague {
    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent(this.getClass().getSimpleName());
    }

    public void doC() {
        System.out.println("doC()");
    }
}
