package com.lql.designpattern.abstractfactorypattern.test1;

import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/6 9:33
 **/
@Data
public class Cpu {
    private String name;

    public Cpu(String name) {
        this.name = name + "-" + this.getClass().getSimpleName();
        System.out.println("生产自【" + name + " 】");
    }
}
