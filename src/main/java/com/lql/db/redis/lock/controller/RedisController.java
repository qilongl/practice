//package com.lql.redis.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.lql.SpringbootApplication;
//import com.lql.redis.RedisUtil;
//import com.lql.springboot.dbutils.service.DataSourceUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.sql.DataSource;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by StrangeDragon on 2019/5/23 16:13
// **/
//@RestController
//public class RedisController {
//    @Autowired
//    private RedisUtil redisUtil;
//
//    @RequestMapping(value = "redis")
//    public String get() throws Exception {
//        String test = redisUtil.getConfig("DES_KEY");
//        return "";
//    }
//}
