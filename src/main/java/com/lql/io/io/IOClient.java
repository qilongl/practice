package com.lql.io.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * Created by StrangeDragon on 2019/7/2 16:22
 **/
public class IOClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8001);
                while (true) {
                    socket.getOutputStream().write((new Date() + ":hello world").getBytes());
                    socket.getOutputStream().flush();
                    Thread.sleep(2*1000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
