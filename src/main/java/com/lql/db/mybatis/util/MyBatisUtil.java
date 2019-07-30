package com.lql.db.mybatis.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @Author lql
 * @Date 2017/12/21 16:48
 */
public class MyBatisUtil {
    private static String config= "/mybatis-config.xml";
    public static SqlSessionFactory getSqlSessionFactory(){
        InputStream is= MyBatisUtil.class.getResourceAsStream(config);
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession(){
        SqlSession sqlSession= getSqlSessionFactory().openSession();
        return sqlSession;
    }

    public static void main(String[] args) {
        System.out.println(getSqlSession());
    }
}
