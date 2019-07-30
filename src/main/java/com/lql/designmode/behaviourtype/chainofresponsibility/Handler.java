package com.lql.designmode.behaviourtype.chainofresponsibility;

/**
 * Created by StrangeDragon on 2019/5/15 10:40
 * 使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链发送该请求，直到有一个对象处理它为止。
 **/
public abstract class Handler {
    protected Handler successor; //实则两个处理器共享

    public Handler(Handler successor) {
        this.successor = successor;
    }
    public Handler() {

    }

    protected abstract void handleRequest(Request request);

}
