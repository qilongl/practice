package com.lql.designpattern.behaviour.chainofresponsibility.test1;

/**
 * Created by StrangeDragon on 2019/8/7 10:05
 **/
public enum LocationEnum {
    AnHui("安徽"), Shanghai("上海"), BeiJing("北京"), TianJin("天津");

    public String getName() {
        return name;
    }

    private String name;

    private LocationEnum(String name) {
        this.name = name;
    }
}
