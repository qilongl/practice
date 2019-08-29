package com.lql.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StrangeDragon on 2019/8/23 14:41
 **/
public class Main {
    public static void main(String[] args) {
        City shanghai = new City("上海");
        City beijing = new City("北京");
        City shenzhen = new City("深圳");
        City nanjing = new City("南京");
        City xiamen = new City("厦门");

        //北京站点设置
        //出发
        List<Road> beijingAfterList = new ArrayList<>();
        beijingAfterList.add(new Road(shenzhen, 1));
        beijingAfterList.add(new Road(shanghai, 3));
        beijingAfterList.add(new Road(nanjing, 1));
        beijingAfterList.add(new Road(xiamen, 3));
        beijing.setAfterSuccessor(beijingAfterList);
        //进入
        List<Road> beijingBeforeList = new ArrayList<>();
        beijingBeforeList.add(new Road(shenzhen, 1));
        beijingBeforeList.add(new Road(nanjing, 1));
        beijing.setBeforeSuccessor(beijingBeforeList);


        //上海站点设置
        //出发
        List<Road> shanghaiAfterList = new ArrayList<>();
        shanghaiAfterList.add(new Road(shenzhen, 3));
        shanghai.setAfterSuccessor(shanghaiAfterList);
        //进入
        List<Road> shanghaiBeforeList = new ArrayList<>();
        shanghaiBeforeList.add(new Road(shenzhen, 3));
        shanghaiBeforeList.add(new Road(beijing, 3));
        shanghai.setBeforeSuccessor(shanghaiBeforeList);

        //深圳站点设置
        //出发
        List<Road> shenzhenAfterList = new ArrayList<>();
        shenzhenAfterList.add(new Road(shanghai, 3));
        shenzhenAfterList.add(new Road(xiamen, 2));
        shenzhenAfterList.add(new Road(beijing, 1));
        shenzhen.setAfterSuccessor(shenzhenAfterList);
        //进入
        List<Road> shenzhenBeforeList = new ArrayList<>();
        shenzhenBeforeList.add(new Road(shanghai, 3));
        shenzhenBeforeList.add(new Road(beijing, 1));
        shenzhenBeforeList.add(new Road(nanjing, 5));
        shenzhen.setBeforeSuccessor(shenzhenBeforeList);

        //厦门站点设置
        //出发
        List<Road> xiamenAfterList = new ArrayList<>();
        xiamenAfterList.add(new Road(nanjing, 1));
        xiamen.setAfterSuccessor(xiamenAfterList);
        //进入
        List<Road> xiamenBeforeList = new ArrayList<>();
        xiamenBeforeList.add(new Road(shenzhen, 2));
        xiamenBeforeList.add(new Road(beijing, 3));
        xiamenBeforeList.add(new Road(nanjing, 1));
        xiamen.setBeforeSuccessor(xiamenBeforeList);


        //南京站点设置
        //出发
        List<Road> nanjingAfterList = new ArrayList<>();
        nanjingAfterList.add(new Road(beijing, 1));
        nanjingAfterList.add(new Road(xiamen, 1));
        nanjingAfterList.add(new Road(shenzhen, 5));
        nanjing.setAfterSuccessor(nanjingAfterList);
        //进入
        List<Road> nanjingBeforeList = new ArrayList<>();
        nanjingBeforeList.add(new Road(xiamen, 1));
        nanjingBeforeList.add(new Road(beijing, 1));
        nanjing.setBeforeSuccessor(nanjingBeforeList);


//        beijing.afterSuccessor(beijingAfterList, shenzhen);
//        shenzhen.afterSuccessor(shenzhenAfterList, xiamen);


    }
}
