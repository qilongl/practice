package com.lql.designpattern.structural.appearance.test1;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

/**
 * Created by StrangeDragon on 2019/8/7 16:13
 **/
public class White implements DrawPen {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("白色的笔画（默认画笔）图: 半径：" + radius + ",横坐标：" + x + ",纵坐标：" + y);
    }
}
