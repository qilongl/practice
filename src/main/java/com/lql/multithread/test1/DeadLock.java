package com.lql.multithread.test1;

/**
 * Created by StrangeDragon on 2019/6/18 14:27
 * 线程死锁：无线等待
 **/
public class DeadLock {
    private static Object object1 = new Object();
    private static Object object2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object1) {//获取对象object1锁
                    System.out.println(Thread.currentThread().getName() + "---get object1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "---waiting get object2");
                    synchronized (object2) {//等待获取object2锁，一直等待，导致object1锁也无法释放
                        System.out.println(Thread.currentThread().getName() + "---get object2");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object2) {//获取对象object2锁
                    System.out.println(Thread.currentThread().getName() + "---get object2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "---waiting get object2");
                    synchronized (object1) {//等待获取object1锁，一直等待，导致object2锁也无法释放，最终导致两个线程无线等待，造成死锁
                        System.out.println(Thread.currentThread().getName() + "---get object1");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
