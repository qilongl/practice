package com.lql.spring.ioc;

/**
 * Created by StrangeDragon on 2019/7/22 13:51
 **/
public class MessageServiceImpl implements MessageService {
    @Override
    public String getMessage() {
        return "Hello World ！";
    }
}
