package com.lql.designpattern.structural.bridge.test1;

/**
 * Created by StrangeDragon on 2019/8/7 15:32
 **/
public class Rectangle extends Shape {
    private int x;
    private int y;

    public Rectangle(int x, int y, DrawPen drawPen) {
        super(drawPen);
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        System.out.println("以长：" + x + "，以宽：" + "y 画长方形");
        drawPen.draw(0, x, y);
    }
}
