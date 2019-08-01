package com.lql.spring.ioc.diyioc;

import org.junit.jupiter.api.Test;

/**
 * Created by StrangeDragon on 2019/7/11 9:43
 **/
public class Main {
    @Test
    public void getBean() throws Exception {
        SimpleIoc simpleIoc = new SimpleIoc("G:\\码云\\练习项目\\practice\\src\\main\\java\\com\\lql\\spring\\ioc\\diyioc\\beans.xml");
        Car car = (Car) simpleIoc.getBean("car");
        System.out.println(car);
        Wheel wheel = (Wheel) simpleIoc.getBean("wheel");
        System.out.println(wheel);
    }
}