package com.lql.designpattern.Creational.facotrypattern.test1;

/**
 * Created by StrangeDragon on 2019/8/5 17:32
 **/
public class Main {
    public static void main(String[] args) {
        FoodFactory factory1 = new AFoodFactory();
        Food food1=factory1.getFood("choudoufu");
        food1.taste();
        FoodFactory factory2=new BFoodFactory();
        Food food2=factory2.getFood("xianyu");
        food2.taste();
    }
}
