package com.lql.designmode.observerpattern;

/**
 * Created by lql on 2018/11/26 11:26
 * 设计模式-观察者模式
 * 被观察者/主题
 **/
public interface Observered {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}
