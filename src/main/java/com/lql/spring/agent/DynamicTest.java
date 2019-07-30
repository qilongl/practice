package com.lql.spring.agent;

import com.lql.spring.agent.dynamicagent.jdk.Apple;
import com.lql.spring.agent.dynamicagent.jdk.Banana;
import com.lql.spring.agent.dynamicagent.jdk.Fruit;
import com.lql.util.JsonUtil;
import com.lql.util.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/7/25 9:33
 **/
public class DynamicTest implements Fruit {
    private String before;
    private Class clazz;
    private String after;

    @Override
    public void show() {
        System.out.println(before);
        try {
            Object object = clazz.newInstance();
            if (object instanceof Fruit) {
                ((Fruit) object).show();
            } else {
                throw new UnsupportedOperationException("不支持的类型");
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(after);

    }

    public DynamicTest(Class clazz, String before, String after) {
        this.before = before;
        this.clazz = clazz;
        this.after = after;
    }

    public static void main(String[] args) {
        DynamicTest dynamicApple = new DynamicTest(Apple.class, "1", "2");
        dynamicApple.show();
        System.out.println("--------------");
        DynamicTest dynamicBanana = new DynamicTest(Banana.class, "1", "2");
        dynamicBanana.show();


    }

}
