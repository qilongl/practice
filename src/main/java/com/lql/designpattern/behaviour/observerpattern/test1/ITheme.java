package com.lql.designpattern.behaviour.observerpattern.test1;

/**
 * Created by StrangeDragon on 2019/8/2 10:48
 **/
public interface ITheme {

    public void register(Observer observer);

    public void remove(Observer observer);

    public void notification(String message);
}
