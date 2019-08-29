package com.lql.datastructure.binarytree;

import com.lql.util.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * Created by StrangeDragon on 2019/8/8 17:13
 **/
@Data
public class Main {
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String getString() {
        return threadLocal.get();
    }

    private void setString(String string) {
        threadLocal.set(string);
    }


    public static void main(String[] args) {
        Main main = new Main();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                main.setString(Thread.currentThread().getName());
                System.out.println(main.getString());
            });
            thread.start();
        }
    }
}
