package com.lql.designpattern.structural.appearance.test1;


import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/7 15:25
 * 其实就是将策略模式中的 Picture 对象抽象化了
 **/
@Data
public abstract class Shape {
    protected DrawPen drawPen;

    protected Shape(DrawPen drawPen) {
        this.drawPen = drawPen;
    }

    public abstract void draw();
}
