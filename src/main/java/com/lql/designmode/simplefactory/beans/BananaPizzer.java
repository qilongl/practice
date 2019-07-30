package com.lql.designmode.simplefactory.beans;

/**
 * @Author lql
 * @Date 2018/4/9 15:58
 */
public class BananaPizzer extends Pizzer {
    @Override
    public void step1() {
        System.out.println(this.getClass().getName() + "步骤1");
    }

    @Override
    public void step2() {
        System.out.println(this.getClass().getName() + "步骤2");
    }

    @Override
    public void step3() {
        System.out.println(this.getClass().getName() + "步骤3");
    }

    @Override
    public void step4() {
        System.out.println(this.getClass().getName() + "步骤4");
    }
}
