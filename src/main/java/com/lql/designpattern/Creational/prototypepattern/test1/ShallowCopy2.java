package com.lql.designpattern.Creational.prototypepattern.test1;

/**
 * Created by StrangeDragon on 2019/8/6 15:04
 * 浅拷贝--涉及属性引用
 * 原型模式，其实就是clone，复制又分为浅拷贝和深拷贝
 * Java 的克隆是浅克隆，碰到对象引用的时候，克隆出来的对象和原对象中的引用将指向同一个对象。通常实现深克隆的方法是将对象进行序列化，然后再进行反序列化。
 * 深拷贝可以参考 com.lql.xml.XMLAnalyzer.beans.BusiConfig#myClone()
 * 要实现对象的拷贝，就要实现 Cloneable 接口（尽管是个空接口），否则会报错
 **/
//@Data
public class ShallowCopy2 implements Cloneable {
    private String name;
    private Test test;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public ShallowCopy2() {
        test = new Test();
        System.out.println("构造方法");
    }


    @Override
    protected ShallowCopy2 clone() {
        ShallowCopy2 demo = null;
        try {
            demo = (ShallowCopy2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return demo;
    }

    /**
     * Java 的克隆是浅克隆，碰到对象引用的时候，克隆出来的对象和原对象中的引用将指向同一个对象。
     * 从下面的执行结果中可以看出拷贝的对象内存地址变了，但拷贝出的对象的属性对象test 内存地址和原对象的属性对象 test 的内存地址一样，
     * 也就验证了 “克隆出来的对象和原对象中的引用将指向同一个对象”，这也就是浅拷贝，拷贝和克隆一个意思哈
     * 深拷贝看Demo2
     *
     * @param args
     */
    public static void main(String[] args) {
        ShallowCopy2 demo = new ShallowCopy2();
        demo.setName("lql");
        demo.test.setName("lql");
        System.out.println("原对象地址：" + demo);
        System.out.println("原对象属性地址：" + demo.test);
        System.out.println("====================");
        ShallowCopy2 demoCopy = demo.clone();
        System.out.println("拷贝对象地址：" + demoCopy);
        System.out.println("拷贝象属性地址：" + demoCopy.test);
        System.out.println("====================");
        demo.test.setName("LQL-change");
        System.out.println(demoCopy.test.getName());

        /**
         * 构造方法
         * 原对象地址：com.lql.designpattern.Creational.prototypepattern.test1.ShallowCopy2@7a07c5b4
         * 原对象属性地址：com.lql.designpattern.Creational.prototypepattern.test1.Test@26a1ab54 -----------------------\
         * ====================                                                                                          \
         * 拷贝对象地址：com.lql.designpattern.Creational.prototypepattern.test1.ShallowCopy2@3d646c37                           / 同一个对象，内存地址一样  26a1ab54
         * 拷贝象属性地址：com.lql.designpattern.Creational.prototypepattern.test1.Test@26a1ab54 -----------------------/
         * ====================
         * LQL-change     //由于原对象和拷贝对象对应属性引用的是同一个对象，所以原对象引用属性的变更导致浅拷贝的对象的引用名称也变了，因为是同一个对象。哈哈，写完了才感觉自己说了废话
         */

    }
}
