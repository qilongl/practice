package com.lql.designpattern.structural.appearance.test1;


/**
 * Created by StrangeDragon on 2019/8/7 15:55
 * 哇擦擦，嘚瑟下，看来看了设计模式还是有所进步的呀，都会自己想着法完善了
 **/
public class ShapeMaker {
    //默认的画笔
    private final DrawPen drawPen = new White();
    private Circle circle;
    private Square square;
    private Rectangle rectangle;

    /**
     * 指定画笔
     *
     * @param drawPen
     */
    public ShapeMaker(DrawPen drawPen) {
        circle = new Circle(drawPen);
        square = new Square(drawPen);
        rectangle = new Rectangle(drawPen);
    }

    /**
     * 使用默认的画笔
     */
    public ShapeMaker() {
        circle = new Circle(drawPen);
        square = new Square(drawPen);
        rectangle = new Rectangle(drawPen);
    }


    public void drawCircle(int radius) {
        circle.setRadius(radius);
        circle.draw();
    }

    /**
     * 指定特定的画笔画圆
     *
     * @param radius
     * @param drawPen
     */
    public void drawCircle(int radius, DrawPen drawPen) {
        circle.setDrawPen(drawPen);
        circle.setRadius(radius);
        circle.draw();
    }

    public void drawSquare(int side) {
        square.setSide(side);
        square.draw();
    }

    /**
     * 指定特定的画笔画正方形
     *
     * @param side
     * @param drawPen
     */
    public void drawSquare(int side, DrawPen drawPen) {
        square.setDrawPen(drawPen);
        square.setSide(side);
        square.draw();
    }

    public void drawRectangle(int x, int y) {
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.draw();
    }

    /**
     * 指定特定的画笔画长方形
     *
     * @param x
     * @param y
     * @param drawPen
     */
    public void drawRectangle(int x, int y, DrawPen drawPen) {
        rectangle.setDrawPen(drawPen);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.draw();
    }
}
