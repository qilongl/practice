package com.lql.designpattern.chainofresponsibility.test1;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Random;

/**
 * Created by StrangeDragon on 2019/8/7 11:04
 * 奖品概率
 **/
public class PrizeRuleHandle extends RuleHandle {
    @Override
    public void apply(User user) {
        double prob = new Random().nextDouble();
        if (prob < 0.05) {
            if (PrizeEnum.FIRST_PRIZE.getNum() > 0) {
                PrizeEnum.FIRST_PRIZE.setNum(PrizeEnum.FIRST_PRIZE.getNum() - 1);
                System.out.println(user.getName() + " 恭喜您获得 " + PrizeEnum.FIRST_PRIZE.getName() + "【" + PrizeEnum.FIRST_PRIZE.getPrize() + " 元】");
            } else {
                System.out.println("很遗憾，您没有获奖！");
            }
        } else if (prob > 0.05 && prob < 0.15) {
            if (PrizeEnum.SECOND_PRIZE.getNum() > 0) {
                PrizeEnum.SECOND_PRIZE.setNum(PrizeEnum.SECOND_PRIZE.getNum() - 1);
                System.out.println(user.getName() + " 恭喜您获得 " + PrizeEnum.SECOND_PRIZE.getName() + "【" + PrizeEnum.SECOND_PRIZE.getPrize() + "元】");
            } else {
                System.out.println("很遗憾，您没有获奖！");
            }
        } else if (prob > 0.15 && prob < 0.4) {
            if (PrizeEnum.THIRD_PRIZE.getNum() > 0) {
                PrizeEnum.THIRD_PRIZE.setNum(PrizeEnum.THIRD_PRIZE.getNum() - 1);
                System.out.println(user.getName() + " 恭喜您获得 " + PrizeEnum.THIRD_PRIZE.getName() + "【" + PrizeEnum.THIRD_PRIZE.getPrize() + "元】");
            } else {
                System.out.println("很遗憾，您没有获奖！");
            }
        } else if (prob > 0.4 && prob < 0.8) {
            if (PrizeEnum.PARTICIPATION_PRIZE.getNum() > 0) {
                PrizeEnum.PARTICIPATION_PRIZE.setNum(PrizeEnum.PARTICIPATION_PRIZE.getNum() - 1);
                System.out.println(user.getName() + " 恭喜您获得 " + PrizeEnum.PARTICIPATION_PRIZE.getName() + "【" + PrizeEnum.PARTICIPATION_PRIZE.getPrize() + "元】");
            } else {
                System.out.println("很遗憾，您没有获奖！");
            }
        } else {
            System.out.println("很遗憾，您没有获奖！");
        }
    }
}
