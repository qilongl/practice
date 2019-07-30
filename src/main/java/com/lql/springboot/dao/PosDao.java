package com.lql.springboot.dao;

/**
 * Created by lql on 2019/1/25 12:36
 **/
public class PosDao {
    public static String getAllOrderListSql(){
        return "SELECT uo.objid,uo.user_objid,su.objname, to_char(uo.crt_time,'yyyy-mm-dd hh24:mi:ss') AS orderTime,\n" +
                "decode(uo.statue,1,'已完成','0','未完成','') AS statue,uo.total\n" +
                " FROM User_Order uo \n" +
                "LEFT JOIN Sys_User su \n" +
                "ON uo.user_objid = su.objid\n" +
                " WHERE 1=1 ";
    }
    public static String getAllFoodMenu(){
        return "SELECT FM.OBJID, FM.OBJNAME, FM.OBJCODE AS CODE, FM.PRICE, FM.STOCK, FM.IS_SALE,FM.IMG_URL \n" +
                " FROM FOOD_MENU FM\n" +
                " WHERE FM.IS_DELETE = 0\n";
    }
}
