package com.lql.designpattern.observerpattern.test1;

/**
 * Created by StrangeDragon on 2019/8/2 9:28
 **/
public interface Observer {
    void update(String message, String themeName);

    void follow(Theme theme);

    void unFollow(Theme theme);
}