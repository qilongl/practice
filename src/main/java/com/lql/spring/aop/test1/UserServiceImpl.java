package com.lql.spring.aop.test1;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by StrangeDragon on 2019/7/24 9:55
 **/
@Service("userService")
public class UserServiceImpl implements UserService {
    public static Map<String, Object> userMap = new HashMap<>();

    @Override
    public User CreateUser(User user) {
        userMap.put(user.getName(), user);
//        System.out.println(user.toString());
//        System.out.println(userMap.size());
        return user;
    }

    @Override
    public Map getUserList() {
        int a=1/0;
        return userMap;
    }
}
