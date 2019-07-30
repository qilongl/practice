package com.lql.designmode.simplefactory.controller;

import com.lql.designmode.simplefactory.beans.Pizzer;
import com.lql.designmode.simplefactory.service.PizzerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lql
 * @Date 2018/4/9 16:03
 */
@RestController
public class PizzerController {
    @Autowired
    PizzerFactory pizzerFactory;

    @RequestMapping("diancan")
    public void dingcan(String type) {
        Pizzer pizzer = pizzerFactory.getPizzer(type);
        pizzer.step1();
        pizzer.step2();
        pizzer.step3();
        pizzer.step4();
    }


}
