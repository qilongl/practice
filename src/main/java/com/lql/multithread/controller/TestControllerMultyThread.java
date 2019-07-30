package com.lql.multithread.controller;

import com.lql.multithread.beans.Thread2;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lql
 * @Date 2018/7/19 10:11
 */
@RestController
public class TestControllerMultyThread {

    public static void main(String[] args) throws InterruptedException {
        //直接调用run方法只是相当于执行了一个方法，并没有单启动一个新的线程，所以会先先执行完该方法才会去执行下面的动作
////        thread1.run();
//        Thread thread1 =new Thread(new Thread1("thread1"));
//        Thread thread2 =new Thread(new Thread1("thread2"));
//        thread1.start();
//        thread2.start();
//        System.out.println("-------------------");

//        Thread thread3 = new Thread(new Thread1("线程3"));
//        thread3.start();
//        thread2.join();
//        thread3.sleep(1000);
//        Thread3 thread3=new Thread3();
//        Thread thread1=new Thread(new Thread1("线程1"));
//        thread1.start();
//        thread3.start();
//        thread3.join();
//        Thread.sleep(10000);
//        System.out.println("主线程启动!");
//        thread3.flag=false;
        Thread2 thread1=new Thread2("线程1");
        Thread2 thread2=new Thread2("线程2");
        thread1.start();
        thread2.start();
        thread2.setPriority(Thread.NORM_PRIORITY+2);
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            System.out.println("我是main线程!");
        }
    }

}
