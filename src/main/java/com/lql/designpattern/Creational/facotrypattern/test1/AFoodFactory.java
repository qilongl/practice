package com.lql.designpattern.Creational.facotrypattern.test1;


/**
 * Created by StrangeDragon on 2019/8/5 17:28
 **/
public class AFoodFactory implements FoodFactory {
    @Override
    public Food getFood(String name) {
        Food food = null;
        if ("xianyu".equalsIgnoreCase(name)) {
            food = new XianYu("A");
        } else if ("choudoufu".equalsIgnoreCase(name)) {
            food = new ChouDouFu("A");
        }
        return food;
    }
}
