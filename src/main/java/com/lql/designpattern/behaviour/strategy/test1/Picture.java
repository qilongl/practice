package com.lql.designpattern.behaviour.strategy.test1;

/**
 * Created by StrangeDragon on 2019/8/6 17:33
 **/
public class Picture {
    private DrawPen drawPen;

    public Picture(DrawPen drawPen) {
        this.drawPen = drawPen;
    }

    public void draw(int radius, int x, int y) {
        drawPen.draw(radius, x, y);
    }
}
