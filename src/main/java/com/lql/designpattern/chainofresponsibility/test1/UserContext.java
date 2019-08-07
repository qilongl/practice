package com.lql.designpattern.chainofresponsibility.test1;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by StrangeDragon on 2019/8/7 9:45
 * 规则配置项
 **/
public class UserContext {
    public static Map<String, User> users = new HashMap<>();
    public static Set<LocationEnum> loactions = new HashSet<>();

    static {
        register(new User("张三",LocationEnum.AnHui));
        register(new User("李四",LocationEnum.AnHui));
        register(new User("王五",LocationEnum.BeiJing));

        loactions.add(LocationEnum.AnHui);
        loactions.add(LocationEnum.Shanghai);
        loactions.add(LocationEnum.BeiJing);
    }

    public static void register(User user) {
        if (isNewUser(user)) {
            users.put(user.getName(), user);
        } else {
            throw new UnsupportedOperationException("用户已存在，请勿重复添加!");
        }
    }

    public static boolean isNewUser(User user) {
        return !users.containsKey(user.getName());
    }

}
