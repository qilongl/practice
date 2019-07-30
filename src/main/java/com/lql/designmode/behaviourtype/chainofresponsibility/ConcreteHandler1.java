package com.lql.designmode.behaviourtype.chainofresponsibility;

/**
 * Created by StrangeDragon on 2019/5/15 10:46
 **/
public class ConcreteHandler1 extends Handler {

    public ConcreteHandler1(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (RequestType.TYPE1 == request.getType()) {
            System.out.println(request.getName() + " is handle by ConcreteHandler1");
            return;
        }
        if (null != successor) {
            successor.handleRequest(request);
        }

    }
}
