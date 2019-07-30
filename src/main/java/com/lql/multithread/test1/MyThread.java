package com.lql.multithread.test1;

/**
 * Created by lql on 2019/2/11 16:33
 * 线程不共享数据
 **/
public class MyThread extends Thread {
    private static int count = 5;

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
            for (int i = 0; i <count ; i++) {
            System.out.println(getName() + "计算 count为：" + count--);
                Thread.yield();
            }
    }

    public static void main(String[] args) {
        MyThread test1 = new MyThread("线程1");
        MyThread test2 = new MyThread("线程2");
        MyThread test3 = new MyThread("线程3");
        test1.start();
        test2.start();
        test3.start();
    }
}
