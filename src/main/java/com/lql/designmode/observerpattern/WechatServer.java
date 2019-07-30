package com.lql.designmode.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lql on 2018/11/26 11:42
 * 服务/主题/被观察者实现类
 **/
public class WechatServer implements Observered {
    private List<Observer> list;
    private String message;

    public WechatServer(){
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!list.isEmpty()){
            list.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i <list.size() ; i++) {
            Observer observer = list.get(i);
            observer.update(message);
        }
    }

    public void sendMsgToAllObservers(String message){
        this.message = message;
        System.out.println("微信服务开始向关注者推送消息!");
        notifyObservers();
    }
}
