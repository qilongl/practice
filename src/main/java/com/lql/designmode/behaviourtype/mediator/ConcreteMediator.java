package com.lql.designmode.behaviourtype.mediator;

/**
 * Created by StrangeDragon on 2019/5/15 17:27
 **/
public class ConcreteMediator extends Mediator {
    private A a;
    private B b;
    private C c;
    private D d;

    public ConcreteMediator(A a, B b, C c, D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public void doEvent(String eventType) {
        switch (eventType) {
            case "A":
                handleA();
                break;
            case "B":
                handleB();
                break;
            case "C":
                handleC();
                break;
            case "D":
                handleD();
                break;
        }

    }

    public void handleA() {
        a.doA();
        b.doB();
        c.doC();
        d.doD();
    }

    public void handleB() {
        b.doB();
        c.doC();
        d.doD();
    }

    public void handleC() {
        c.doC();
        d.doD();
    }

    public void handleD() {
        d.doD();
    }


}
