package com.lql.designpattern.behaviour.chainofresponsibility.test1;

/**
 * Created by StrangeDragon on 2019/8/7 9:45
 * 是否新用户校验
 **/
public class UserRuleHandle extends RuleHandle {
    @Override
    public void apply(User user) {
        if (UserContext.isNewUser(user)) {
            UserContext.register(user);
            System.out.println(user.getName() + " 为新用户！");
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(user);
            }
        } else {
            System.out.println(user.getName() + " 为老用户，无法参与抽奖，感谢的关注！");
        }

    }
}
