package com.lql.designmode.observerpattern;

/**
 * Created by lql on 2018/11/26 11:52
 * 观察者实现类
 **/
public class ObserverImpl implements Observer {
    private String name;

    public ObserverImpl(String name, Observered observered) {
        this.name = name;
        observered.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        System.out.println(name + "收到推送消息：" + msg);
    }
}
