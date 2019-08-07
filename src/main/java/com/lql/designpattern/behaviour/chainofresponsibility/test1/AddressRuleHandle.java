package com.lql.designpattern.behaviour.chainofresponsibility.test1;

/**
 * Created by StrangeDragon on 2019/8/7 10:01
 * 抽奖地区规则校验
 **/
public class AddressRuleHandle extends RuleHandle {
    @Override
    public void apply(User user) {
        if (UserContext.loactions.contains(user.getAddress())) {
            System.out.println(user.getName() + " 用户所在地区符合抽奖规则，欢迎参与本次抽奖活动！");
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(user);
            }
        } else {
            StringBuffer sb = new StringBuffer();
            for (LocationEnum address : UserContext.loactions) {
                sb.append(address.getName()).append("  ");
            }
            System.out.println("本次抽奖的地区范围：" + sb.toString());
            System.out.println(user.getName() + " 用户所在地区【"+user.getAddress().getName()+"】 不符合抽奖规则，感谢您的参与！");
        }
    }
}
