package com.lql.springboot.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author lql
 * @Date 2017/12/20 18:15
 */
@Service
public class BookService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getAll(){
        String sql="select * from book";
        String result= JSON.toJSONString(jdbcTemplate.queryForList(sql));
        return result;
    }


}
