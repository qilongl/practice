package com.lql.designpattern.abstractfactorypattern.test1;

/**
 * Created by StrangeDragon on 2019/8/6 9:32
 **/
public interface ComputerFactory {
    Cpu makeCpu();

    MainBoard makeMainBoard();

    HardDisk makeHardDisk();
}
