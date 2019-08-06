package com.lql.designpattern.facotrypattern.test1;

/**
 * Created by StrangeDragon on 2019/8/5 17:28
 **/
public class BFoodFactory implements FoodFactory{
    @Override
    public Food getFood(String name) {
        Food food = null;
        if ("xianyu".equalsIgnoreCase(name)) {
            food = new XianYu("B");
        } else if ("choudoufu".equalsIgnoreCase(name)) {
            food = new ChouDouFu("B");
        }
        return food;
    }
}
