package com.lql.designmode.behaviourtype.mediator;

/**
 * Created by StrangeDragon on 2019/5/15 16:21
 **/
public class A implements Colleague {
    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent(this.getClass().getSimpleName());
    }

    public void doA() {
        System.out.println("doA()");
    }
}
