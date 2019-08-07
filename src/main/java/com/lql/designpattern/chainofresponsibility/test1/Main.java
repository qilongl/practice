package com.lql.designpattern.chainofresponsibility.test1;

/**
 * Created by StrangeDragon on 2019/8/7 10:30
 * 责任链模式
 * 就是流程审核一样，可以灵活配置流程节点以及节点的审核顺序（后继节点）
 * 抽奖：
 * 1、定义抽奖规则抽象类，到底有多少规则，规则如何校验，无限自增
 * 2、自用组合流程节点以及规则校验顺序（就是流程节点的先后审核顺序）
 **/
public class Main {
    /**
     * 以下测试保证了每个用户只能抽奖一次，设置了奖品得到概率（当然设计个抽奖概率算法更好了，这里仅为简单实现）
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * ===========================抽奖规则设置开始==========================
         */
        //新用户校验规则
        UserRuleHandle userRuleHandle = new UserRuleHandle();
        //抽奖地区校验
        AddressRuleHandle addressRuleHandle = new AddressRuleHandle();
        //奖品
        PrizeRuleHandle prizeRuleHandle = new PrizeRuleHandle();
        /**
         * 先后顺序设置，将地区校验规则作为用户校验的直接后继，则表示先校验用户，再校验地区
         * 假设地区规则之后还有其他的校验规则，则将其他规则作为地区校验规则的直接后继即可
         *
         * */
        addressRuleHandle.setSuccessor(prizeRuleHandle);
        userRuleHandle.setSuccessor(addressRuleHandle);
        /**
         * ===========================抽奖规则设置结束==========================
         */


        /**
         * 抽奖规则校验开始
         * 责任链模式，由责任链开头，一直走完整个责任链，跟单流程很像
         */
        userRuleHandle.apply(new User("张三"));
        System.out.println("==========================================");
        userRuleHandle.apply(new User("LQL", LocationEnum.AnHui));
        userRuleHandle.apply(new User("LQL1", LocationEnum.AnHui));
        userRuleHandle.apply(new User("LQL2", LocationEnum.AnHui));
        userRuleHandle.apply(new User("LQL3", LocationEnum.AnHui));
        System.out.println("==========================================");
        userRuleHandle.apply(new User("lql", LocationEnum.TianJin));
        System.out.println("==========================================");
        System.out.println("一等奖剩余：" + PrizeEnum.FIRST_PRIZE.getNum());
        System.out.println("二等奖剩余：" + PrizeEnum.SECOND_PRIZE.getNum());
        System.out.println("三等奖剩余：" + PrizeEnum.THIRD_PRIZE.getNum());
        System.out.println("四等奖剩余：" + PrizeEnum.PARTICIPATION_PRIZE.getNum());
    }
}
