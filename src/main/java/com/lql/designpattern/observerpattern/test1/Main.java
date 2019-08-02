package com.lql.designpattern.observerpattern.test1;

/**
 * Created by StrangeDragon on 2019/8/2 10:06
 * 观察者模式，实现用户关注公众号，公众号推送消息
 * 如果看过我的笔记的话可以知道： Theme 和其子类之间我也用了模板方法模式
 **/
public class Main {
    public static void main(String[] args) {
        Theme taobao = new ThemeTaoBao();
        Theme jd = new ThemeJD();
        Theme pdd = new ThemePdd("拼多多");
        Observer observer1 = new ObserverImpl("用户1");
        Observer observer2 = new ObserverImpl("用户2");
        Observer observer3 = new ObserverImpl("用户3");
        Observer observer4 = new ObserverImpl("用户4");
        observer1.follow(taobao);
        observer2.follow(taobao);
        observer3.follow(taobao);
        observer4.follow(taobao);
        taobao.notification("淘宝搜索“淘宝人生”，查看自己的淘宝消费记录！");

        observer1.follow(jd);
        observer2.follow(jd);
        observer3.follow(jd);
        observer4.follow(jd);
        jd.notification("我阿东倒霉呀，被陷害了！");

        observer1.follow(pdd);
        observer2.follow(pdd);
        observer3.follow(pdd);
        observer4.follow(pdd);
        pdd.notification("我拼多多各种优惠，便宜。。。不服来买");


        observer1.unFollow(taobao);
        observer1.unFollow(jd);
        observer1.unFollow(pdd);
        taobao.notification("竟然有人取关了，桑西");

    }
}
