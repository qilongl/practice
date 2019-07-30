package com.lql.springboot.util;

/**
 * Created by lql on 2018/9/27 16:25
 **/
public class BaseSqlUtil {
    /**
     * sql 分页
     *
     * @param sql
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static String paging(String sql, String pageNum, String pageSize) {
        String pagingSql = "SELECT * FROM(SELECT A.*,ROWNUM RN FROM (" + sql + ") A " +
                "WHERE ROWNUM < " + (Integer.valueOf(pageSize) * Integer.valueOf(pageNum) + 1) + ") WHERE RN>" + (Integer.valueOf(pageNum) - 1) * Integer.valueOf(pageSize);
        return pagingSql;
    }

    public static String count(String sql) {
        String countSql = "SELECT COUNT(1) AS TOTAL FROM (" + sql + ") A";
        return countSql;
    }
}
