package com.lql.designpattern.Creational.abstractfactorypattern.test1;

/**
 * Created by StrangeDragon on 2019/8/6 9:40
 **/
public class HUAWEIComputerFactory implements ComputerFactory {
    @Override
    public Cpu makeCpu() {
        return new Cpu("HUAWEI");
    }

    @Override
    public MainBoard makeMainBoard() {
        return new MainBoard("HUAWEI");
    }

    @Override
    public HardDisk makeHardDisk() {
        return new HardDisk("HUAWEI");
    }
}
