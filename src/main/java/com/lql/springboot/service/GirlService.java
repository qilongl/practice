package com.lql.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GirlService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllList(){
        String sql="select * from girl";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public Map<String,Object> getInfoById(Integer id){
        String sql = "select * from girl t where t.id = ?";
        return jdbcTemplate.queryForMap(sql,id);
    }
}
