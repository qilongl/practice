package com.lql.designpattern.behaviour.observerpattern.test1;

import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/2 9:29
 **/
@Data
public class ObserverImpl implements Observer {
    private String name;
    private String message;

    public ObserverImpl(String name) {
        this.name = name;
    }

    @Override
    public void update(String message, String themeName) {
        System.out.println(themeName + "推送了消息：" + message);
    }

    public void follow(Theme abstarctTheme) {
        abstarctTheme.register(this);
    }

    public void unFollow(Theme abstarctTheme) {
        abstarctTheme.remove(this);
    }
}
