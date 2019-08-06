package com.lql.designpattern.Creational.prototypepattern.test1;

import java.io.Serializable;

/**
 * Created by StrangeDragon on 2019/8/6 15:35
 **/
public class Test5 implements Serializable {
    private DeepCopy deepCopy;

    public Test5(DeepCopy deepCopy) {
        this.deepCopy = deepCopy;
    }
}
