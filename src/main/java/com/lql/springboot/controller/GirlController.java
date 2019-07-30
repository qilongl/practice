package com.lql.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.lql.SpringbootApplication;
import com.lql.springboot.dbutils.service.DataSourceUtil;
import com.lql.springboot.service.GirlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;

@RestController
public class GirlController {
    private static final Logger logger = LoggerFactory.getLogger(GirlController.class);

    private static final long time= 2 * 1000;

    @Autowired
    GirlService girlService;

    @RequestMapping(value = "datasource" )
    public String getDataSource(HttpServletRequest req) throws Exception {
        DataSource dataSource= DataSourceUtil.getDataSource(SpringbootApplication.ctx);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select 1 from dual");
        System.out.println(dataSource.getClass().toString());
        return JSON.toJSONString(list);
    }

//            DataSource dataSource= (DataSource) ctx.getBean("dataSource");
//            System.out.println(dataSource);
//            return dataSource.getClass().toString()+"";

//        if (dataSource!=null){
//            return dataSource.getName();
//        }else {
//            return "容器中不存在该Bean";
//        }

    @RequestMapping(value = "getAll" )
    public List<Map<String,Object>> getAll(){
        return girlService.getAllList();
    }

    @RequestMapping(value = "getInfoById" )
    public String getInfoById(@RequestParam("id") Integer id){
        return toJSONString(girlService.getInfoById(id));
    }

//    @Scheduled(cron = "* * * * * ?")
    public void testScheduled(){
        logger.info("当前时间为:"+new Date());
//        System.out.println(System.currentTimeMillis());
    }
}
