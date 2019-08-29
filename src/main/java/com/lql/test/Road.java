package com.lql.test;

import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/23 14:49
 **/
@Data
public class Road {
    private City city;
    private int path;

    public Road(City city, int path) {
        this.city = city;
        this.path = path;
    }
}
