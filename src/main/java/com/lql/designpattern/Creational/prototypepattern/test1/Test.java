package com.lql.designpattern.Creational.prototypepattern.test1;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

/**
 * Created by StrangeDragon on 2019/8/6 15:18
 **/
//@Data
public class Test {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Test() {
    }
}
