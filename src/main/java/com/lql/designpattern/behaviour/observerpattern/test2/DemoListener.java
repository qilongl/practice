package com.lql.designpattern.behaviour.observerpattern.test2;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by StrangeDragon on 2019/8/2 10:59
 **/
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        String msg = event.getMessage();
        System.out.println("接收的消息：" + msg);
    }
}
