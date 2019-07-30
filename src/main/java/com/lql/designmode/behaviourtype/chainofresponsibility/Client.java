package com.lql.designmode.behaviourtype.chainofresponsibility;

/**
 * Created by StrangeDragon on 2019/5/15 10:52
 **/
public class Client {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1(null);//参数需要 Handler 对象，第一次没有，直接赋值
        Handler handler2 = new ConcreteHandler2(handler1);
        Request request1 = new Request(RequestType.TYPE1, "请求1");
        handler2.handleRequest(request1);

        Request request2 = new Request(RequestType.TYPE2, "请求2");
        handler2.handleRequest(request2);
    }
}
