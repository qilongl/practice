package com.lql.multithread.controller;

/**
 * @Author lql
 * @Date 2018/7/20 10:30
 */
/*
 * 范例名称：生产者--消费者问题   (每生产一个消费一个)
 */
public class ProAndCre implements Runnable {
    private String name;
    private int age;
    boolean flag = true;//表示可以生产
    int i = 0;

    public synchronized void producer() throws InterruptedException {
//        for (int i = 0; i < 50; i++) {
        while (!flag) {
            this.wait();//让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法”，当前线程被唤醒(进入“就绪状态”)。
        }
        Thread.sleep(10);
        this.name = Thread.currentThread().getName() + "我是生产者";
//            this.age = i;
        System.out.println(this.name + "  " + i++);
        flag = false;
        this.notify();
    }
//    }

    public synchronized void customer() throws InterruptedException {
//        for (int i = 0; i < 50; i++) {
        while (flag) {
            this.wait();
        }
        Thread.sleep(10);
        this.name = Thread.currentThread().getName() + "我是消费者";
//            this.age = i;
        System.out.println(this.name + "  " + i++);
        flag = true;
        this.notify();
    }

//    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ProAndCre proAndCre = new ProAndCre();
        Thread thread1 = new Thread(proAndCre, "线程1");
        Thread thread2 = new Thread(proAndCre, "线程2");
        Thread thread3 = new Thread(proAndCre, "线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
