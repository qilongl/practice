package com.lql.designpattern.behaviour.statue.test;

import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/7 13:56
 **/
@Data
public class Context {
    private Statue statue;

    public Context(Statue statue) {
        this.statue = statue;
    }

    public void effect() {
        statue.handle();
    }
}
