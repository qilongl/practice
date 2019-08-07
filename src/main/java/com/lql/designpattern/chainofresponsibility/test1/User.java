package com.lql.designpattern.chainofresponsibility.test1;

import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/7 9:46
 **/
@Data
public class User {
    private String name;
    private LocationEnum address;

    public User(String name) {
        this.name = name;
    }

    public User(String name, LocationEnum address) {
        this.name = name;
        this.address = address;
    }
}
