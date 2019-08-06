package com.lql.designpattern.Creational.prototypepattern.test1;

/**
 * Created by StrangeDragon on 2019/8/6 15:04
 * 浅拷贝--不涉及属性引用
 * 原型模式，其实就是clone，复制又分为浅拷贝和深拷贝
 * Java 的克隆是浅克隆，碰到对象引用的时候，克隆出来的对象和原对象中的引用将指向同一个对象。通常实现深克隆的方法是将对象进行序列化，然后再进行反序列化。
 * 要实现对象的拷贝，就要实现 Cloneable 接口（尽管是个空接口），否则会报错
 * 深拷贝可以参考 DeepCopy
 **/
//@Data
public class ShallowCopy1 implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShallowCopy1() {
        System.out.println("构造方法");
    }


    @Override
    protected ShallowCopy1 clone() {
        ShallowCopy1 shallowCopy1 = null;
        try {
            shallowCopy1 = (ShallowCopy1) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return shallowCopy1;
    }

    /**
     * 拷贝对象不会执行构造方法，是直接拷贝的堆上的二进制，没有通过new 来创建的对象
     *
     * @param args
     */
    public static void main(String[] args) {
        ShallowCopy1 shallowCopy1 = new ShallowCopy1();
        shallowCopy1.setName("lql");
        System.out.println(shallowCopy1);
        ShallowCopy1 shallowCopy1Copy = shallowCopy1.clone();
        System.out.println(shallowCopy1Copy);
    }
}
