package com.lql.designmode.behaviourtype.mediator;

/**
 * Created by StrangeDragon on 2019/5/15 17:36
 **/
public class Client {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();
        Mediator mediator = new ConcreteMediator(a, b, c, d);
        d.onEvent(mediator);
    }
}
