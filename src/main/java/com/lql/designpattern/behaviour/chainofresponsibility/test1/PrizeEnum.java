package com.lql.designpattern.behaviour.chainofresponsibility.test1;

import java.util.Random;

/**
 * Created by StrangeDragon on 2019/8/7 11:04
 **/
public enum PrizeEnum {
    FIRST_PRIZE("一等奖", 10000, 10),
    SECOND_PRIZE("二等奖", 1000, 100),
    THIRD_PRIZE("三等奖", 100, 1000),
    PARTICIPATION_PRIZE("参与奖", 10, 10000);

    private String name;
    private Object prize;
    private int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPrize() {
        return prize;
    }

    public void setPrize(Object prize) {
        this.prize = prize;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private PrizeEnum(String name, Object prize, int num) {
        this.name = name;
        this.prize = prize;
        this.num = num;
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextDouble());
    }
}
