package com.lql.designpattern.abstractfactorypattern.test1;

import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/6 9:37
 **/
@Data
public class Computer {
    private Cpu cpu;
    private MainBoard mainBoard;
    private HardDisk hardDisk;

    public Computer(Cpu cpu, MainBoard mainBoard, HardDisk hardDisk) {
        this.cpu = cpu;
        this.mainBoard = mainBoard;
        this.hardDisk = hardDisk;
    }

}
