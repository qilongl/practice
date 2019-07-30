package com.lql.multithread.test1;

/**
 * Created by lql on 2019/2/11 16:44
 * 线程共享数据时
 **/
public class MyThread2 extends Thread {
    private int count = 5;

    public MyThread2() {

    }

    public MyThread2(String name) {
        super(name);
    }


    @Override
    public synchronized void run() {
        if (count > 0) {
            count--;
            System.out.println(Thread.currentThread().getName() + " 计算，count为：" + count);
        }
    }

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        Thread thread1 = new Thread(myThread2, "线程1");
        Thread thread2 = new Thread(myThread2, "线程2");
        Thread thread3 = new Thread(myThread2, "线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
