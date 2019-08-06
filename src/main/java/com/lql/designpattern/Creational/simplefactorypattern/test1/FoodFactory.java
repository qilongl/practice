package com.lql.designpattern.Creational.simplefactorypattern.test1;

/**
 * Created by StrangeDragon on 2019/8/5 17:16
 **/
public class FoodFactory {
    public static Food getFood(String name) {
        Food food = null;
        if ("xianyu".equalsIgnoreCase(name)) {
            food = new XianYu();
        } else if ("choudoufu".equalsIgnoreCase(name)) {
            food = new ChouDouFu();
        }
        return food;
    }
}
