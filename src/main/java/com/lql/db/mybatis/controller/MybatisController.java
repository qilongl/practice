package com.lql.db.mybatis.controller;

import com.lql.db.mybatis.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lql
 * @Date 2017/12/21 17:02
 */
@RestController
public class MybatisController {

    @RequestMapping(value = "mybatis")
    public String getOneBook(){
        String statement="com.lql.mybatis.mappings.xmlmaps.bookMapper.getBook";
        SqlSession sqlSession =MyBatisUtil.getSqlSession();
        return sqlSession.selectOne(statement,1).toString();
    }

    @RequestMapping(value = "qqq")
    public String test(){
        return "<center><p1>电费水费</p1></center>";
    }
}
