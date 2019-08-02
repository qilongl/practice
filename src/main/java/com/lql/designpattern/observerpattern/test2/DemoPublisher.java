package com.lql.designpattern.observerpattern.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by StrangeDragon on 2019/8/2 11:01
 **/
@Component
public class DemoPublisher {
    @Autowired
    ApplicationContext applicationContext;

    public void publish(String message) {
        applicationContext.publishEvent(new DemoEvent(this, message));
    }
}
