package com.lql.springboot.controller;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;


@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String servername;

    @RequestMapping(value = "/test")
    public String test() {
        return "hello " + servername;
    }


    public static void main(String[] args) {
//        Jedis jedis = new Jedis("127.0.0.1");
//        System.out.println(jedis.ping());
//        System.out.println(jedis.lrange("name",0,5));

//arrayList插入int
        int n = 100000;
        arrayListInsertfunction(n);
        linkListInsertfunction(n);
    }

    public static void arrayListInsertfunction(int n) {
        List list1 = new ArrayList<>();
        long a = new Date().getTime();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                list1.add(i / 2, "新增序列" + ++j);
            }
        }
        long b = new Date().getTime();
        System.out.println("ArrayList 插入 " + n + " 次的时间：" + (b - a) + " 毫秒");
        for (int i = 0; i < n; i++) {
            list1.get(new Random().nextInt(list1.size()));
        }
        long c = new Date().getTime();
        System.out.println("ArrayList 查询 " + n + " 次的时间：" + (c - b) + " 毫秒");

    }

    public static void linkListInsertfunction(int n) {
        List list1 = new LinkedList();
        long a = new Date().getTime();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                list1.add(i / 2, "新增序列" + ++j);
            }
        }
        long b = new Date().getTime();
        System.out.println("LinkedList 插入 " + n + " 次的时间：" + (b - a) + " 毫秒");
        for (int i = 0; i < n; i++) {
           list1.get(new Random().nextInt(list1.size()));
        }
        long c = new Date().getTime();
        System.out.println("LinkedList 查询 " + n + " 次的时间：" + (c - b) + " 毫秒");

    }


    public static String getUUID() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

    public static String split() {
        String temp = "2017-12";
        String[] params = temp.split("-");
        String year = params[0];
        String month = params[1];
        return year + " " + month;
    }
}
