package com.lql.designmode.behaviourtype.strategy;

/**
 * Created by StrangeDragon on 2019/5/16 9:54
 **/
public class Client {
    public static void main(String[] args) {
        BehaviorOperator behaviorOperator = new BehaviorOperator();
        Behavior1 behavior1=new Behavior1();
        Behavior2 behavior2=new Behavior2();
        behaviorOperator.setStrategy(behavior1);
        behaviorOperator.touch();
        behaviorOperator.setStrategy(behavior2);
        behaviorOperator.touch();
    }
}
