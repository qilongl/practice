package com.lql.spring.aop.test1;

import java.util.Map;

/**
 * Created by StrangeDragon on 2019/7/24 9:53
 **/
public interface UserService {
    User CreateUser(User user);

    Map getUserList();
}
