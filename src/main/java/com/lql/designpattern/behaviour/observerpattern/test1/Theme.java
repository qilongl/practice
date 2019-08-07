package com.lql.designpattern.behaviour.observerpattern.test1;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StrangeDragon on 2019/8/2 9:29
 **/
@Data
public  class Theme implements ITheme {
    public List<Observer> list = new ArrayList<>();

    private String name;

    public Theme() {
        this.name = this.getClass().getSimpleName();
    }

    public Theme(String name) {
        this.name = name;
    }

    public void register(Observer observer) {
        list.add(observer);
        System.out.println("感谢您关注 【" + getName() + "】！");
    }

    public void remove(Observer observer) {
        list.remove(observer);
        System.out.println("已成功取消对 【" + getName() + "】的关注！");
    }

    public void notification(String message) {
        for (Observer observer : list) {
            observer.update(message, getName());
        }
    }
}
