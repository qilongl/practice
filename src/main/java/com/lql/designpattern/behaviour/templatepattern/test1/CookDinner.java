package com.lql.designpattern.behaviour.templatepattern.test1;

/**
 * Created by StrangeDragon on 2019/8/1 16:41
 * 模板模式-做饭
 * 步骤1、准备食材
 * 步骤2、做饭
 * 步骤3、吃
 * <p>
 * 做饭所选用的食材、做饭的烹饪方式、吃饭的姿势可能都不一样，所以全让子类来实现，定义为抽象类，如果觉得有公共的步骤，也可以父类中实现
 **/
public abstract class CookDinner {

    public void templateFunction() {
        prepareFood();
        cook();
        eat();
    }

    protected abstract void prepareFood();

    protected abstract void cook();

    protected abstract void eat();
}
