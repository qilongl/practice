package com.lql.jvm;

/**
 * Created by StrangeDragon on 2019/6/27 16:36
 * 1、重写equals、hashcode
 * 2、String字符串的特例
 **/
public class EqualsTest {
    private String name;
    private int age;


    private static final String aa = "aa";
    private static final String bb = "bb";
    private static final String cc;
    private static final String dd;

    static {
        cc = "cc";
        dd = "dd";
    }

    /**
     * aa和bb都是常量，值是固定的，因此c 的值也是固定的，它在类被编译时就已经确定了。也就是说：String c=aa+bb; 等同于：String c="aa"+"bb";
     */
    private static void test1() {
        String d = "aa" + "bb";
        String c = aa + bb;
        String e = "aabb";
        System.out.println(c == d);
        System.out.println(d == e);
        System.out.println(c == e);
    }

    /**
     * cc和dd虽然被定义为常量，但是它们都没有马上被赋值。在运算出c的值之前，他们何时被赋值，以及被赋予什么样的值，都是个变数。
     * 因此cc和dd在被赋值之前，性质类似于一个变量。那么c就不能在编译期被确定，而只能在运行时被创建了。
     */
    private static void test2() {
        String d = "cc" + "dd";
        String c = cc + dd;
        String e = "ccdd";
        System.out.println(c == d);
        System.out.println(d == e);
        System.out.println(c == e);
    }

    public static void main(String[] args) {
        test1();
        System.out.println("--------------------");
        test2();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    String a = new String();
    Integer b = Integer.valueOf(40);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EqualsTest that = (EqualsTest) o;

        if (age != that.age) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}
