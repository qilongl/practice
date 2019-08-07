package com.lql.designpattern.structural.appearance.test1;

/**
 * Created by StrangeDragon on 2019/8/7 15:34
 * 门面模式
 * 桥接模式虽然解耦了，但每次需要什么对象我们都要先实例化一个对象，然后再调用其方法。有没有更加直接点的方法？
 * 门面模式就是将这几个 Shape实现类的方法统一起来，像画什么类型的图直接调用相应的方法即可,使用起来更加方便，
 * 对客户端来讲比较友好，不需要画个什么图案再去实例化响应的实例了，因为门面模式在构造方法中已经将各个图案的实例给初始化好了
 * 当然也有相应的弊端，如果图案类较多，由于没有采用延迟加载（懒加载），当画一个图案时会初始化所有的图案类实例，会得不偿失
 **/
public class Main {
    public static void main(String[] args) {
        /**
         * 使用默认画笔话各种形状
         */
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle(5);
        System.out.println("------------------------------------");
        shapeMaker.drawRectangle(5, 6);
        System.out.println("------------------------------------");
        shapeMaker.drawSquare(4);
        System.out.println("------------------------------------");
        shapeMaker.drawSquare(6, new YellowPen());
        System.out.println("\n========================================================================\n");

        /**
         * 使用指定颜色的画笔
         */
        ShapeMaker shapeMaker1 = new ShapeMaker(new RedPen());
        shapeMaker1.drawCircle(5);
        System.out.println("------------------------------------");
        shapeMaker1.drawRectangle(5, 6);
        System.out.println("------------------------------------");
        shapeMaker1.drawSquare(4);
        System.out.println("------------------------------------");
        shapeMaker1.drawSquare(6, new YellowPen());
    }
}
