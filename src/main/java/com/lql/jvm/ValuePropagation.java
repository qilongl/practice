package com.lql.jvm;

/**
 * Created by StrangeDragon on 2019/6/28 14:44
 * 值传递
 **/
public class ValuePropagation {
    private static int a = 10;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        a = 100;
        System.out.println();
    }

}
