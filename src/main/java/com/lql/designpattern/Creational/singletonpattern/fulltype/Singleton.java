package com.lql.designpattern.Creational.singletonpattern.fulltype;

/**
 * Created by StrangeDragon on 2019/8/6 10:31
 * 饱汉式
 * 延迟加载（当第一次使用采取加载）
 * 双重检查（线程安全）
 **/
public class Singleton {

    /**
     * 私有化构造方法
     */
    private Singleton() {
    }

    /**
     * 与饿汉式相比，饱汉式不需要先实例化，加了关键字“volatile”
     * 为何加关键字“volatile”，因为是静态变量，所有线程共享，所以当对象被初始化后，为了保证每次读取变量都是最新值。
     */
    private static volatile Singleton singleton = null;

    public static Singleton getInstance() {
        if (singleton == null) {
            //并发时可能此时还没有初始化，所以多线程可能得到的 singleton 都是null
            //所以加锁处理
            synchronized (Singleton.class) {
                //拿到锁后判断 singleton是不是null,防止重复创建对象
                /**
                 * 此时你可能有疑问，说外层if不是判断过了singleton == null了吗？
                 * 举例：并发时 A、B 两个线程都进入了第一个 if 中，此时 A 线程获得锁，然后进入实例化  singleton = new Singleton()，释放锁，
                 * 然后B 线程获取锁，如果不再次判断 singleton == null ，直接进行初始化则会导致重复初始化，
                 * 加了判断后，如果发现  singleton 不为null了则什么也不执行，这样就避免了重复初始化了
                 */
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }


    /**
     * 当然也可以直接在方法上加"synchronized" 关键字
     * 为何不这样做？
     * 这样每次获取对象都要获取锁，性能比较差
     */
//    public static synchronized Singleton getSingleton() {
//        if (singleton == null) {
//            singleton = new Singleton();
//        }
//        return singleton;
//    }
}
