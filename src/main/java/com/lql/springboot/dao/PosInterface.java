package com.lql.springboot.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lql
 * @date 2018/6/10 19:24
 * @describe pos机的后台接口提供
 */
@Service
public interface PosInterface {
    //获取商品菜单列表
    public List<Map<String, Object>> getAllFoodMenu(JdbcTemplate jdbcTemplate);

    //获取热销商品列表
    public List<Map<String, Object>> getOfferFoodlist(JdbcTemplate jdbcTemplate);

    //获取用户常用商品列表
    public String getUserOfferList(String userId);

    //新增订单
    public int addUserOrder(List list, JdbcTemplate jdbcTemplate);

    //新增订单食品
    public int[] addOrderFood(List<Object[]> objects, JdbcTemplate jdbcTemplate);

    //减去商品的库存
    public int[] reduceFoodStock(List<Object[]> objects, JdbcTemplate jdbcTemplate);

    //订单查询
    public List<Map<String, Object>> getAllOrderList(String jsonParams,JdbcTemplate jdbcTemplate);

    //登录
//    public List<Map<String,Object>> login(List list,JdbcTemplate jdbcTemplate);
}