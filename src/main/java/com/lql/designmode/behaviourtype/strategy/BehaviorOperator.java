package com.lql.designmode.behaviourtype.strategy;

/**
 * Created by StrangeDragon on 2019/5/16 9:52
 **/
public class BehaviorOperator {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void touch() {
        if (null != strategy) {
            strategy.behavior();
        }
    }

}
