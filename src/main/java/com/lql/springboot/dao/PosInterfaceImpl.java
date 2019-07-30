package com.lql.springboot.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author lql
 * @date 2018/6/10 19:44
 * @describe
 */
@Component
public class PosInterfaceImpl implements PosInterface {


    @Override
    /**
     *  获取菜单列表
     */
    public List<Map<String, Object>> getAllFoodMenu(JdbcTemplate jdbcTemplate) {
        String sql = "SELECT FM.OBJID, FM.OBJNAME, FM.OBJCODE AS CODE, FM.PRICE, FM.STOCK, FM.IS_SALE\n" +
                "  FROM FOOD_MENU FM\n" +
                " WHERE FM.IS_DELETE = 0\n" +
                " ORDER BY TO_CHAR(FM.OBJCODE)\n";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    /**
     *  获取热销食品列表
     *  最受欢迎的10款商品（从订单中根据商品的订单数倒序取出12款商品，关联商品表获取商品信息，再根据商品表里的库存量升序）
     */
    public List<Map<String, Object>> getOfferFoodlist(JdbcTemplate jdbcTemplate) {
        String sql = "SELECT A.OBJID,FM.OBJNAME,FM.OBJCODE,FM.PRICE,FM.STOCK,FM.IS_SALE,A.ORDERTOTAL FROM(\n" +
                "                SELECT OBJID ,ORDERTOTAL FROM (\n" +
                "                        SELECT FF.FOOD_OBJID AS OBJID,COUNT(1) AS ORDERTOTAL FROM ORDER_FOOD FF GROUP BY FF.FOOD_OBJID ORDER BY ORDERTOTAL DESC)\n" +
                "                WHERE ROWNUM<=12) A\n" +
                "        LEFT JOIN FOOD_MENU FM\n" +
                "        ON A.OBJID = FM.OBJID\n" +
                "        WHERE FM.IS_DELETE=0\n" +
                "        ORDER BY ORDERTOTAL DESC, STOCK";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    /**
     *  获取登录用户的常用食品列表
     */
    public String getUserOfferList(String userId) {
        return null;
    }

    @Override
    /**
     *  添加订单
     */
    public int addUserOrder(List list, JdbcTemplate jdbcTemplate) {
        String sql = "INSERT INTO USER_ORDER\n" +
                "  (ID, OBJID, USER_OBJID, STATUE, TOTAL)\n" +
                "VALUES\n" +
                "  (SYS_ID.NEXTVAL, ?, ?, to_number(?), to_number(?))\n";
        int result = jdbcTemplate.update(sql, list.toArray());
        return result;
    }


    /**
     * 订单对应的商品
     *
     * @param list
     * @return
     */
    public int[] addOrderFood(List<Object[]> list, JdbcTemplate jdbcTemplate) {
        String sql = "INSERT INTO ORDER_FOOD\n" +
                "  (ID, ORDER_OBJID, FOOD_OBJID,FOOD_COUNT)\n" +
                "VALUES\n" +
                "  (SYS_ID.NEXTVAL,?, ?,?)\n";
        int[] result = jdbcTemplate.batchUpdate(sql, list);
        return result;
    }

    /**
     * 更新商品库存
     *
     * @param 、count 、UPD_PSN 、foodObjId、count
     * @param
     * @return
     */
    @Override
    public int[] reduceFoodStock(List<Object[]> objects, JdbcTemplate jdbcTemplate) {
        String sql = "UPDATE FOOD_MENU FM\n" +
                "   SET FM.STOCK = FM.STOCK - ?, FM.UPD_PSN = ?\n" +
                " WHERE FM.OBJID =?\n" +
                "   AND FM.IS_SALE = 1\n" +
                "   AND FM.IS_DELETE = 0\n" +
                "   AND FM.STOCK >= ?\n";
        int[] result = jdbcTemplate.batchUpdate(sql, objects);
        return result;
    }

    @Override
    /**
     *  获取所有订单列表
     */
    public List<Map<String, Object>> getAllOrderList(String jsonParams,JdbcTemplate jdbcTemplate) {
        String sql = "SELECT uo.objid,uo.user_objid,su.objname, to_char(uo.crt_time,'yyyy-mm-dd hh24:mi:ss') AS orderTime,\n" +
                "decode(uo.statue,1,'已完成','0','未完成','') AS statue,uo.total\n" +
                " FROM User_Order uo \n" +
                "LEFT JOIN Sys_User su \n" +
                "ON uo.user_objid = su.objid\n" +
                "ORDER BY orderTime DESC";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

}
