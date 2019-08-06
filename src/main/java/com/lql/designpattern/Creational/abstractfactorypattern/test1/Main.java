package com.lql.designpattern.Creational.abstractfactorypattern.test1;

/**
 * Created by StrangeDragon on 2019/8/6 9:44
 **/
public class Main {
    public static void main(String[] args) {
        ComputerFactory huaweiCpmputerFactory = new HUAWEIComputerFactory();

        Cpu cpu = huaweiCpmputerFactory.makeCpu();

        MainBoard mainBoard = huaweiCpmputerFactory.makeMainBoard();

        HardDisk hardDisk = huaweiCpmputerFactory.makeHardDisk();

        Computer huaweiComputer = new Computer(cpu, mainBoard, hardDisk);
        System.out.println(huaweiComputer.toString());
    }
}
