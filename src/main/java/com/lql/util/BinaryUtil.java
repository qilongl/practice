package com.lql.util;

/**
 * Created by StrangeDragon on 2019/8/5 9:26
 **/
public class BinaryUtil {

    /**
     * 十进制转二进制
     * @param num
     * @return
     */
    public static String toBinary(int num) {
        String str = "";
        while (num != 0) {
            str = num % 2 + str;
            num = num / 2;
        }
        return str;
    }


    public static void main(String[] args) {
        System.out.println(toBinary(11));
        /**
         * 二进制或者其他进制转十进制
         * @param args
         */
        System.out.println(Integer.parseInt("1000", 2));
    }
}
