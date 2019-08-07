package com.lql.designpattern.structural.appearance.test1;

import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/7 15:31
 **/
@Data
public class Square extends Shape {
    private int side;

    public Square( DrawPen drawPen) {
        super(drawPen);
    }

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
