package com.lql.designpattern.structural.bridge.test1;

/**
 * Created by StrangeDragon on 2019/8/7 15:31
 **/
public class Square extends Shape {
    private int side;

    public Square(int side, DrawPen drawPen) {
        super(drawPen);
        this.side = side;
    }

    @Override
    public void draw() {
        System.out.println("以边长 " + side + " 画正方形");
        drawPen.draw(0, side, side);
    }
}
