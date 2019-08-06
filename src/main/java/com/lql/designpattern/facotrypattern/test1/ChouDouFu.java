package com.lql.designpattern.facotrypattern.test1;

/**
 * Created by StrangeDragon on 2019/8/5 17:29
 **/
public class ChouDouFu implements Food {
    private String from;

    public ChouDouFu(String from) {
        this.from = from;
    }

    @Override
    public void taste() {
        System.out.println("来自：【" + from + "】的臭豆腐");
    }


}
