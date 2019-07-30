package com.lql.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.lql.springboot.service.ViewsService;
import com.lql.util.JsonValueFilter;
import com.lql.util.ResponseResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by lql on 2018/8/30 13:15
 * <p>
 * 用于直接传递查询视图所返还的结果集
 **/
@CrossOrigin
@RestController
@RequestMapping("/ext")
public class ViewsController {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    ViewsService viewsService;

    @PostMapping("/views")
    public String list(@RequestBody Map map) {
        ResponseResult rr = viewsService.getlist(map,null);
        log.info(rr);
        return JSON.toJSONString(rr,JsonValueFilter.changeNullToString());
    }

    @PostMapping("/getOpsCommonList")
    public String getOpsCommonList(@RequestBody Map map) {
        ResponseResult rr = viewsService.getOpsCommonList(map);
        log.info(rr);
        return JSON.toJSONString(rr,JsonValueFilter.changeNullToString());
    }

    //    @RequestMapping("/initDataByTableName") //在GET请求中，不能使用@RequestBody。
    @PostMapping("/initDataByTableName")
    public String init(@RequestBody Map map) throws SQLException {
        ResponseResult rr = viewsService.init(map);
        log.info(rr);
        return JSON.toJSONString(rr,JsonValueFilter.changeNullToString());
    }

    @PostMapping("/getTableAndView")
    public String getTableAndView() {
        ResponseResult rr = viewsService.getTableAndView();
        log.info(rr);
        return JSON.toJSONString(rr,JsonValueFilter.changeNullToString());
    }

    public static void main(String[] args) {
    }

}
