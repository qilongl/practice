package com.lql.designpattern.builderpattern.test1;

import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/6 13:33
 * 建造者模式:是将一个复杂的对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 * 在用户不知道对象的建造过程和细节的情况下就可以直接创建复杂的对象。
 * 核心就是：通过建造器（UserBuilder） 将属性设置好，然后调用 build（）方法，将属性复制给实际产生的对象
 **/
@Data
public class User {
    private String name;
    private int age;


    /**
     * 私有化构造方法，防止客户端直接调用构造方法。否则要建造者干嘛
     */
    private User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 静态方法，用于生成一个Builder
     * 比让客户端直接去 new 一个 UserBuilder 对象要美观的多
     *
     * @return
     */
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    /**
     * ===========================================分割线===================================
     */

    public static class UserBuilder {
        //属性和 User 中的属性保持一致
        private String name;
        private int age;

        /**
         * 如果需要控制属性是否必填，可以在构造方法中调用
         */
        private UserBuilder() {
        }


        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        /**
         * 负责将 UserBuild 中设置的属性复制到User中，再次可以做一些校验什么的
         *
         * @return
         */
        public User build() {

            if (name == null) {
                throw new UnsupportedOperationException("用户名不能为空");
            }
            if (age > 150 || age < 0) {
                throw new UnsupportedOperationException("我擦我就想知道谁活了那么久。。。，");
            }
            //...

            User user = new User(name, age);

            return user;
        }
    }


}
