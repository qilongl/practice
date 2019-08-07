package com.lql.designpattern.structural.appearance.test1;


/**
 * Created by StrangeDragon on 2019/8/6 17:31
 **/
public class YellowPen implements DrawPen {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("黄色的笔画图: 半径：" + radius + ",横坐标：" + x + ",纵坐标：" + y);
    }
}
