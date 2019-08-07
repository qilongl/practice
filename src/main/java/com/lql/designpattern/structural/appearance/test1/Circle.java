package com.lql.designpattern.structural.appearance.test1;

import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/7 15:27
 **/
@Data
public class Circle extends Shape {
    //半径
    private int radius;

    public Circle(DrawPen drawPen){
        super(drawPen);
    }

    public Circle(int radius, DrawPen drawPen) {
        //super()放在第一行，因为需要先初始化父类
        super(drawPen);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("以半径 " + radius + " 画圆");
        drawPen.draw(radius, 0, 0);

    }
}
