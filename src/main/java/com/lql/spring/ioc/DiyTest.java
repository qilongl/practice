package com.lql.spring.ioc;

import org.omg.CORBA.MARSHAL;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/7/22 14:58
 **/
public class DiyTest {
    public static void main(String[] args) {
        Map<String, Object> map = new LinkedHashMap();
        map.put("1", 2);
        map.put("2", 2);
        for (Map.Entry<String, Object> x : map.entrySet()) {
            System.out.println(x.getKey() + ":"+x.getValue());
        }
        map.clear();
        for (Map.Entry<String, Object> x : map.entrySet()) {
            System.out.println(x.getKey() + ":"+x.getValue());
        }
    }
}
