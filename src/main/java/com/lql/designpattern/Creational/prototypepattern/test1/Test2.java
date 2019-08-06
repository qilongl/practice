package com.lql.designpattern.Creational.prototypepattern.test1;

import java.io.Serializable;

/**
 * Created by StrangeDragon on 2019/8/6 15:34
 **/
public class Test2 implements Serializable{
    private DeepCopy deepCopy;

    public Test2(DeepCopy deepCopy) {
        this.deepCopy = deepCopy;
    }
}
