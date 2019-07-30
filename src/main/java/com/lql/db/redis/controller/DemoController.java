package com.lql.db.redis.controller;

import com.lql.db.redis.service.DemoService;
import com.lql.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by StrangeDragon on 2019/6/24 9:55
 **/
@RestController
@RequestMapping("/redis")
public class DemoController {
    @Autowired
    DemoService demoService;

    @RequestMapping("refreshCache")
    public ResponseResult test() {
        return demoService.refreshCache();
    }

    @RequestMapping("getFunctionList")
    public ResponseResult getFunctionList() {
        return demoService.getFunctionList();
    }
}
