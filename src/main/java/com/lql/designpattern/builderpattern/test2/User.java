package com.lql.designpattern.builderpattern.test2;

import lombok.Builder;
import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/6 12:29
 *
 * @Builder 完成了test1包中用代码实现的建造者
 **/
@Builder
@Data
public class User {
    private String name;
    private String flag;
    private int age;

    public static void main(String[] args) {
        User user = User.builder().age(10).flag("T").name("LQL").build();
        System.out.println(user);
    }
}
