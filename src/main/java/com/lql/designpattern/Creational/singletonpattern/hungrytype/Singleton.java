package com.lql.designpattern.Creational.singletonpattern.hungrytype;

import java.util.Date;

/**
 * Created by StrangeDragon on 2019/8/6 9:56
 * 饿汉式
 * 非延迟加载
 * 线程安全（类初始化的时候进行静态变量 singleton 的初始化，由于是静态变量，所以不存在线程安全问题）
 **/
public class Singleton {
    /**
     * 私有化构造方法，防止外部类实例化，保证单例
     */
    private Singleton() {
    }

    /**
     * 创建私有静态变量，当该类第一次使用的时候进行创建
     */
    private static Singleton singleton = new Singleton();

    public static Singleton getInstance() {
        return singleton;
    }

    /**
     * 随便写一个静态方法。这里想说的是，如果我们只是要调用 Singleton.getDate()，
     * 本来是不想要生成 Singleton 实例的，不过没办法，已经生成了
     *
     * --------分割线--------
     * 类什么时候被初始化？
     * 1.创建一个类的实例，也就是说new一个对象的时候
     * 2.访问某个类或者接口的中的静态变量，或者对静态变量赋值的时候
     * 3.调用类的静态方法
     * 4.反射（class.forName(“com.ysd.entity”)）
     * 5.初始化一个类的子类 （首先会先初始化它的父类）
     * 6.JVM启动时标明的启动类，就是文件名和类名相同的那个类
     * ---------------------
     *
     * 这里你可能有疑问，因为为什么会生成Singleton实例？
     * 因为调用类的静态方法的时候类会进行初始化，所以当第一次调用Singleton.getDate()方法时类进行初始化，静态变量singleton就进行初始化了，
     * 所以该实例就生成了，网上所说的饿汉式的不足之处应该就是这里，引用网上的一句话：
     * “你定义了一个单例的类，不需要其实例，可是你却把一个或几个你会用到的静态方法塞到这个类中”
     *
     * @return
     */
    public static Date getDate() {
        return new Date();
    }
}
