package com.lql.designmode.behaviourtype.command;

/**
 * Created by StrangeDragon on 2019/5/15 11:25
 **/
public class LightOffCommand implements Command {

    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
