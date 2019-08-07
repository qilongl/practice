package com.lql.designpattern.behaviour.templatepattern.test2;

/**
 * Created by StrangeDragon on 2019/8/1 17:34
 * 模拟JDBC
 **/
public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = userDao.findUser(2);
        System.out.println(user);
    }
}
