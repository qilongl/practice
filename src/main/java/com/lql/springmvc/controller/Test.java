package com.lql.springmvc.controller;

import java.io.*;

/**
 * Created by lql on 2018/9/30 10:55
 **/
public class Test {
    public String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\lql\\Desktop\\人员.txt");
        byte[] buf = new byte[1024];
        FileOutputStream fileOutputStream = new FileOutputStream("人员1.txt");
        int length = 0;
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            while ((length = fileInputStream.read(buf)) != -1) {
                outputStreamWriter.write(new String(buf,"utf-8").toCharArray());
                outputStreamWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
