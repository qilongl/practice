package com.lql.designpattern.builderpattern.test1;

/**
 * Created by StrangeDragon on 2019/8/6 13:53
 * 温馨提示：你可能已经注意到了，建造者模式有好多重复性代码（建造器和目标对象的属性其实是重复的，如果不是特殊情况，一般的方法也是固定的）
 * 然而这些都不用担心，lombok 都帮我们做了 ，请参考test2.User里的注解@Builder
 **/
public class Main {
    public static void main(String[] args) {
        User user = User.builder().name("LQL").age(24).build();
        System.out.println(user);
    }
}
