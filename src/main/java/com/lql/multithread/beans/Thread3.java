package com.lql.multithread.beans;

import java.util.Date;

/**
 * @Author lql
 * @Date 2018/7/19 12:28
 */
public class Thread3 extends Thread {
    public static boolean flag = true;

    public void run() {
        while (flag) {
            System.out.println(new Date().toString());
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
