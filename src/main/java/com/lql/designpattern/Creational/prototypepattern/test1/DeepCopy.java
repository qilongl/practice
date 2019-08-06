package com.lql.designpattern.Creational.prototypepattern.test1;

import java.io.*;

/**
 * Created by StrangeDragon on 2019/8/6 15:04
 * 深拷贝
 * 原型模式，其实就是clone，复制又分为浅拷贝和深拷贝
 * Java 的克隆是浅克隆，碰到对象引用的时候，克隆出来的对象和原对象中的引用将指向同一个对象。
 * 通常实现深克隆的方法是将对象进行序列化，然后再进行反序列化。
 * 注意事项：
 * 1、序列化的对象，其属性对象也需要实现序列化接口
 * 2、用transient关键字标记的成员变量不参与序列化过程
 **/
//@Data
public class DeepCopy implements Serializable {
    private Test2 test2 = new Test2(this);
    private Test3 test3 = new Test3(this);
    private Test4 test4 = new Test4(this);
    private Test5 test5 = new Test5(this);

    public DeepCopy() {
        System.out.println("构造方法");
    }

    public DeepCopy(String name, String flag) {
        System.out.println("构造方法");
        this.name = name;
        this.flag = flag;
    }

    private String name;

    /**
     * 被 transient 关键字修饰的属性字段不会被拷贝
     * 用transient关键字标记的成员变量不参与序列化过程
     */
    private transient String flag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void show() {
        System.out.println(this);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        System.out.println(test5);
    }

    /**
     * 通过对象序列化实现深拷贝：将对象序列化为字节序列后，默认会将该对象的整个对象图进行序列化，再通过反序列即可完美地实现深拷贝
     * 不过要注意的是，如果某个属性被 transient 修饰，那么该属性就无法被拷贝了。
     * 深拷贝
     * 1、也不会执行构造方法
     * 2、深拷贝中的属性引用与原对象中的引用不再是同一个对象
     *
     * @return
     */
    public DeepCopy myClone() throws Exception {
        DeepCopy deepCopy = null;
        //将当前对象写入到输出流中，转换为字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        //从字节数组输出流中获取流
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        //从输入流中获取对象
        deepCopy = (DeepCopy) ois.readObject();
        return deepCopy;
    }

    @Override
    public String toString() {
        return "DeepCopy{" +
                "test2=" + test2 +
                ", test3=" + test3 +
                ", test4=" + test4 +
                ", test5=" + test5 +
                ", name='" + name + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

    /**
     * 从测试结果可以看出，深拷贝从原对象中完全拷贝了一份出来，拷贝出来的对象不再与原对象有引用关系
     * 深拷贝的对象中被 transient 修饰的属性字段为null
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        DeepCopy deepCopy = new DeepCopy("lql", "T");
        deepCopy.show();
        System.out.println("================================");
        DeepCopy deepCopyCopy = deepCopy.myClone();
        deepCopyCopy.show();
        System.out.println("================================");
        deepCopyCopy.setName("LQL");
        deepCopyCopy.show();


    }
}
