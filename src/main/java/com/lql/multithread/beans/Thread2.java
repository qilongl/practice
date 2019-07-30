package com.lql.multithread.beans;

/**
 * @Author lql
 * @Date 2018/7/19 15:55
 */
public class Thread2 extends Thread {
    public Thread2(String name) {
        super(name);
    }

    public void run() {
        for (int i = 1; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println("我是"+getName() + "  " + i+" 我让让你，下面的那个小线程!");
                yield();
            }
        }
    }

}
