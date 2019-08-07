package com.lql.designpattern.chainofresponsibility.test1;

import lombok.Data;

/**
 * Created by StrangeDragon on 2019/8/7 9:43
 * 抽奖规则抽象类
 **/
@Data
public abstract class RuleHandle {
    //直接后继，责任链模式就像一个流程审核，直接后继（successor） 相当于下一个节点
    private RuleHandle successor;

    public abstract void apply(User user);
}
