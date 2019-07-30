package com.lql.designmode.observerpattern;

/**
 * Created by lql on 2018/11/26 11:55
 **/
public class ObserverPatternTest {
    public static void main(String[] args) {
        WechatServer server = new WechatServer();
        Observer observer1 = new ObserverImpl("张三", server);
        Observer observer2 = new ObserverImpl("李四", server);
        Observer observer3 = new ObserverImpl("王五", server);
//        server.registerObserver(observer1);
//        server.registerObserver(observer2);
//        server.registerObserver(observer3);
        server.sendMsgToAllObservers("今天周一，美好的一周开始了，午饭时间到！");
    }
}
