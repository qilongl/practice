package com.lql.multithread.beans;

/**
 * @Author lql
 * @Date 2018/7/19 10:14
 */
public class Thread1 implements Runnable {
    private int num = 50;

    public Thread1(String name) {
        Thread.currentThread().setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            sell();
        }
    }

    public synchronized void sell() {
        if (num > 0) {
            num--;
            System.out.println(Thread.currentThread() + " 售出一张票,剩余 "+num);
        }
    }

    public static void main(String[] args) {
        Thread1 thread = new Thread1("");
        Thread thread1 = new Thread(thread, "窗口1");
        Thread thread2 = new Thread(thread, "窗口2");
        thread1.start();
        thread2.start();
    }
}
