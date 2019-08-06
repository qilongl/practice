package com.lql.designpattern.Creational.prototypepattern.test1;

import java.io.Serializable;

/**
 * Created by StrangeDragon on 2019/8/6 15:34
 **/
public class Test3 implements Serializable {
    private DeepCopy deepCopy;

    public Test3(DeepCopy deepCopy) {
        this.deepCopy = deepCopy;
    }
}
