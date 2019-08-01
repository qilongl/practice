package com.lql.spring.ioc.diyioc;

/**
 * Created by StrangeDragon on 2019/7/10 17:17
 **/
public class Wheel {
    private String nums;
    private String texture;

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "nums=" + nums +
                ", texture='" + texture + '\'' +
                '}';
    }
}
