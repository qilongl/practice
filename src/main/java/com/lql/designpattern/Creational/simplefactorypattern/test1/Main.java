package com.lql.designpattern.Creational.simplefactorypattern.test1;

/**
 * Created by StrangeDragon on 2019/8/5 17:19
 **/
public class Main {
    public static void main(String[] args) {
        Food food1 = FoodFactory.getFood("xianyu");
        food1.taste();
        Food food2 = FoodFactory.getFood("choudoufu");
        food2.taste();
    }
}
