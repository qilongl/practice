package com.lql.designpattern.abstractfactorypattern.test1;

/**
 * Created by StrangeDragon on 2019/8/6 9:40
 **/
public class LenovoComputerFactory implements ComputerFactory {
    @Override
    public Cpu makeCpu() {
        return new Cpu("Lenovo");
    }

    @Override
    public MainBoard makeMainBoard() {
        return new MainBoard("Lenovo");
    }

    @Override
    public HardDisk makeHardDisk() {
        return new HardDisk("Lenovo");
    }
}
