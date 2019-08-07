package com.lql.designpattern.structural.agency.test1;

/**
 * Created by StrangeDragon on 2019/8/7 14:28
 **/
public class ServiceProxy implements IService {
    private IService service = new ServiceImpl();

    @Override
    public void say() {
        System.out.println("我是哔哩哔哩喷子的代理人，我开个头，下面开喷。。");
        service.say();
        System.out.println("如何？，喷的如何？哈哈哈");
    }

}
