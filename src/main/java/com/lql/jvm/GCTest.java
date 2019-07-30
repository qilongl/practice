package com.lql.jvm;

/**
 * Created by StrangeDragon on 2019/7/1 10:50
 **/
public class GCTest {
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3;
        allocation1=new byte[30900*1024];
        allocation2=new byte[900*1024];
        allocation3=new byte[5000*1024];
    }
}
