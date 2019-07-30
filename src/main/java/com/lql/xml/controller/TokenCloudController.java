package com.lql.xml.controller;

import com.alibaba.fastjson.JSON;
import com.lql.SpringbootApplication;
import com.lql.util.DateFormater;
import com.lql.xml.db.DPFactory;
import com.lql.xml.db.DPService;
import com.lql.xml.service.TokenCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/11/03.
 */
@RestController
public class TokenCloudController {
    @Autowired
    TokenCloudService tokenCloudService;

    @RequestMapping(value = "/getTokenInfoByAccount")
    public String getTokenInfoByAccount(@RequestParam String account) {
        String result = "";
        DPService dpService = DPFactory.createDPService(SpringbootApplication.ctx);
        try{
        List<Map<String, Object>> tokenObjectArray=tokenCloudService.getTokenInfoByAccount(dpService,account);
        if(tokenObjectArray.size()>0){
        Map<String, Object> tokenObject = tokenObjectArray.get(0);
        Date end_time = (Date)tokenObject.get("END_TIME");
        tokenObject.replace("END_TIME", DateFormater.yyyyMMddHHmmss(end_time));
        result  = JSON.toJSONString(tokenObject);
        }
        }catch (Exception ex) {
            ex.printStackTrace();
            result = "";

        }

        return result;
    }
}
