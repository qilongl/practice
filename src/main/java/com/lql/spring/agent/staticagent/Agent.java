package com.lql.spring.agent.staticagent;

/**
 * Created by StrangeDragon on 2019/7/11 11:01
 * 静态代理
 **/
public class Agent implements Person {
    private Actorer actorer;
    private String before;
    private String after;

    public Agent(Actorer actorer, String before, String after) {
        this.actorer = actorer;
        this.before = before;
        this.after = after;
    }

    @Override
    public void say() {
        System.out.println(">>>>>>>>>>>" + before);
        actorer.say();
        System.out.println(">>>>>>>>>>>" + after);
    }


    public static void main(String[] args) {
        Agent agent = new Agent(new Actorer(), "BB之前先酝酿一下", "BB之后稍微休息一下");
        agent.say();
    }
}
