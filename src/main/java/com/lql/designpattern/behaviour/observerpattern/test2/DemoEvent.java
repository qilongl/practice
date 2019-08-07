package com.lql.designpattern.behaviour.observerpattern.test2;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * Created by StrangeDragon on 2019/8/2 10:56
 **/
@Data
public class DemoEvent extends ApplicationEvent {
    private String message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DemoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
