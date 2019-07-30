package com.lql.xml.XMLAnalyzer.converter;

import com.lql.xml.XMLAnalyzer.service.impl.BusiConfigCache;
import com.lql.xml.db.DPFactory;
import com.lql.xml.db.DPService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2018/1/25.
 * 检测作者署名资格,提交人是否可以写该报告
 */
public class checkAuthorPermisson {

    private static Logger logger = LoggerFactory.getLogger(checkAuthorPermisson.class);

    public List<Map<String, Object>> check(List<Map<String, Object>> rptInduRat, List<Map<String, Object>> rptSecRat, List<Map<String, Object>> rptAuthor, List<Map<String, Object>> rptExch, List<Map<String, Object>> sumbmitParams) throws Exception {
        DPService dpService = DPFactory.createDPService(BusiConfigCache.ctx);
        List<Map<String, Object>> finnalRs = new ArrayList<>();
        String person = String.valueOf(sumbmitParams.get(0).get("person"));
        String rpttype = String.valueOf(sumbmitParams.get(0).get("rpttype"));
                /*
        * sysadmin无需校验
        * */
        if ("sysadmin".equalsIgnoreCase(person)) {
            Map<String, Object> rs = new HashedMap();
            rs.put("statuscode", "200");
            finnalRs.add(rs);
            return finnalRs;
        }
        /*
* 校验是否有报告提交权限
* */
        String checkIsPermissonSql = "\n" +
                "SELECT COUNT(1) AS total\n" +
                "            FROM UF_RPT_TYPE urt\n" +
                "            LEFT JOIN Sys_Cst_Info sct ON sct.cst_type='1624'\n" +
                "                AND sct.is_delete='0'\n" +
                "                AND urt.SUB_TIT_FORMAT=sct.cst_num\n" +
                "            LEFT JOIN SYS_CST_INFO SCTQ\n" +
                "                    ON SCTQ.CST_TYPE = '1003' --ͼƬ\n" +
                "                    AND SCTQ.IS_DELETE = '0'\n" +
                "                    AND URT.IMG_FILE = SCTQ.CST_NUM\n" +
                "            LEFT JOIN SYS_DATA_BASE SDB\n" +
                "                ON urt.OBJID = SDB.OBJID\n" +
                "\n" +
                "                JOIN (\n" +
                "SELECT ID,                                                       MAX(IS_READ) AS IS_READ,\n" +
                " CASE WHEN length(LISTAGG(IS_READ_EXTEND,',') WITHIN GROUP(ORDER BY IS_READ_EXTEND))=3 THEN '0'\n" +
                " ELSE LISTAGG(IS_READ_EXTEND,',') WITHIN GROUP(ORDER BY IS_READ_EXTEND)   END AS IS_READ_EXTEND,\n" +
                "                                                                MAX(IS_WRITE) AS IS_WRITE ,\n" +
                " CASE WHEN length(LISTAGG(IS_WRITE_EXTEND,',') WITHIN GROUP(ORDER BY IS_WRITE_EXTEND))=3 THEN '0'\n" +
                " ELSE LISTAGG(IS_WRITE_EXTEND,',') WITHIN GROUP(ORDER BY IS_WRITE_EXTEND) END AS IS_WRITE_EXTEND\n" +
                "    FROM (\n" +
                "            SELECT UBTP.FK_TYPE_ID AS ID,UBTP.IS_READ ,UBTP.IS_WRITE,'0' AS IS_READ_EXTEND,'0' AS IS_WRITE_EXTEND\n" +
                "                        FROM UF_BAS_TYPE_PERM UBTP\n" +
                "                        LEFT JOIN UF_RPT_TYPE USLT\n" +
                "                            ON USLT.objid = UBTP.fk_type_id\n" +
                "                        LEFT JOIN SYS_DATA_BASE SDB\n" +
                "                            ON USLT.OBJID = SDB.OBJID\n" +
                "                        WHERE  SDB.IS_DELETE =0\n" +
                "                            AND UBTP.SR_TYPE='10'\n" +
                "                            AND UBTP.TARGET_OBJ_ID IN (\n" +
                "                                                    SELECT sus.fk_station_id FROM Sys_User_Station sus\n" +
                "                                                    WHERE sus.is_delete=0 AND sus.fk_user_id = '" + person + "')\n" +
                "            UNION\n" +
                "            SELECT  ID,IS_READ,IS_WRITE,\n" +
                "            CASE WHEN IS_READ ='1' THEN '1' ELSE '0' END   AS IS_READ_EXTEND,\n" +
                "            CASE WHEN IS_WRITE ='1' THEN '1' ELSE '0' END  AS IS_WRITE_EXTEND\n" +
                "            FROM(\n" +
                "                SELECT UBTP.FK_TYPE_ID AS ID,UBTP.IS_READ ,UBTP.IS_WRITE\n" +
                "                       FROM UF_BAS_TYPE_PERM UBTP\n" +
                "                JOIN ( select distinct t.fk_station_id  as ID\n" +
                "                                                        from sys_sta_user_rela_ship t\n" +
                "                                                        where t.fk_p_station_id IN (\n" +
                "                                                                        SELECT sus.fk_station_id FROM Sys_User_Station sus\n" +
                "                                                                        WHERE sus.is_delete=0 AND sus.fk_user_id = '" + person + "')\n" +
                "                     UNION\n" +
                "                            SELECT DISTINCT T.FK_P_ORGUNIT_ID AS ID\n" +
                "                                                         FROM SYS_STA_USER_RELA_SHIP T\n" +
                "                                                         JOIN (SELECT DISTINCT T.FK_ORGUNIT_ID AS ID\n" +
                "                                                                    FROM SYS_STA_USER_RELA_SHIP T\n" +
                "                                                                    WHERE T.FK_STATION_ID IN (\n" +
                "                                                                        SELECT sus.fk_station_id FROM Sys_User_Station sus\n" +
                "                                                                        WHERE sus.is_delete=0 AND sus.fk_user_id = '" + person + "')\n" +
                "                                                               )W1\n" +
                "                                                         ON  T.FK_ORGUNIT_ID = W1.ID\n" +
                "                     UNION\n" +
                "                            SELECT DISTINCT T.FK_ORGUNIT_ID AS ID\n" +
                "                                                         FROM SYS_STA_USER_RELA_SHIP T\n" +
                "                                                         JOIN (SELECT DISTINCT T.FK_ORGUNIT_ID AS ID\n" +
                "                                                                    FROM SYS_STA_USER_RELA_SHIP T\n" +
                "                                                                    WHERE T.FK_STATION_ID IN (\n" +
                "                                                                        SELECT sus.fk_station_id FROM Sys_User_Station sus\n" +
                "                                                                        WHERE sus.is_delete=0 AND sus.fk_user_id = '" + person + "')\n" +
                "                                                              )W2\n" +
                "                                                         ON T.FK_ORGUNIT_ID =W2.ID\n" +
                "                     )T2\n" +
                "                ON UBTP.TARGET_OBJ_ID = T2.ID\n" +
                "                WHERE  UBTP.SR_TYPE='10'\n" +
                "                )\n" +
                "            ) A\n" +
                "            GROUP BY a.id\n" +
                "            )    B\n" +
                "        ON urt.objid =  B.ID\n" +
                "\n" +
                "        WHERE SDB.IS_DELETE =0 \n" +
                "        and IS_WRITE = 1\n" +
                "        AND urt.OBJID = '" + rpttype + "'\n";
        String getMenuSql = " SELECT COUNT(1) AS TOTAL FROM ( select r.fk_menu_id as id,\n" +
                "            m.objcode as CODE,\n" +
                "            m.objname as NAME,\n" +
                "            m.objtype || '-' || m.url || '-' || m.img_file || '-' || m.dsp_order || '-' ||\n" +
                "            m.option_type as FLAG\n" +
                "            from (\n" +
                "\n" +
                "            select distinct mp.fk_menu_id\n" +
                "            from sys_sta_user_rela_ship t\n" +
                "            join sys_menu_perm mp\n" +
                "            on t.fk_p_orgunit_id = mp.target_obj_id\n" +
                "            and mp.target_obj_type = 10  and mp.is_delete=0\n" +
                "            where t.fk_user_id = '" + person + "'\n" +
                "            and t.fk_orgunit_id != t.fk_p_orgunit_id\n" +
                "            union\n" +
                "\n" +
                "            select distinct mp.fk_menu_id\n" +
                "            from sys_sta_user_rela_ship t\n" +
                "            join sys_menu_perm mp\n" +
                "            on t.fk_orgunit_id = mp.target_obj_id\n" +
                "            and mp.target_obj_type = 10  and mp.is_delete=0\n" +
                "            where t.fk_user_id = '" + person + "'\n" +
                "            and t.fk_orgunit_id != t.fk_p_orgunit_id\n" +
                "            union\n" +
                "\n" +
                "            select distinct mp.fk_menu_id\n" +
                "            from sys_sta_user_rela_ship t\n" +
                "            join sys_menu_perm mp\n" +
                "            on t.fk_station_id = mp.target_obj_id\n" +
                "            and mp.target_obj_type = 11 and mp.is_delete=0\n" +
                "            where t.fk_p_user_id = '" + person + "'\n" +
                "            union\n" +
                "\n" +
                "            select distinct mp.fk_menu_id\n" +
                "            from sys_sta_user_rela_ship t\n" +
                "            join sys_menu_perm mp\n" +
                "            on t.fk_station_id = mp.target_obj_id\n" +
                "            and mp.target_obj_type = 11 and mp.is_delete=0\n" +
                "            where t.fk_user_id = '" + person + "'\n" +
                "            union\n" +
                "\n" +
                "            select mp.fk_menu_id\n" +
                "            from sys_user_role t\n" +
                "            join sys_menu_perm mp\n" +
                "            on t.fk_role_id = mp.target_obj_id\n" +
                "            and mp.target_obj_type = 12 and mp.is_delete=0\n" +
                "            where t.fk_user_id = '" + person + "'\n" +
                "            and t.is_delete = 0) r\n" +
                "            join sys_menu m\n" +
                "            on fk_menu_id = m.objid\n" +
                "            and m.is_delete = 0\n" +
                "            and m.objtype = 10 ) WHERE CODE='301015'";
        List<Map<String, Object>> isPermisson = dpService.select(checkIsPermissonSql);
        List<Map<String, Object>> getMenu = dpService.select(getMenuSql);
        String total1 = String.valueOf(isPermisson.get(0).get("TOTAL"));
        String total2 = String.valueOf(getMenu.get(0).get("TOTAL"));

        if ("0".equals(total1) && "0".equals(total2)) {
            Map<String, Object> rs = new HashedMap();
            rs.put("statuscode", "500");
            Map<String, Object> rs2 = new HashedMap();
            rs2.put("info", "您无权限提交该报告,请联系管理员!");
            finnalRs.add(rs);
            finnalRs.add(rs2);
            return finnalRs;
        }

        /*
* 校验第一作者
* */
        for (int i = 0; i < rptAuthor.size(); i++) {
            Map<String, Object> rptAuthorMap = rptAuthor.get(i);
            String author = String.valueOf(rptAuthorMap.get("FK_USER_ID"));
            String isFirst = String.valueOf(rptAuthorMap.get("IS_FIRST_AUTH"));
            if ("1".equals(isFirst)) {
                String isCanFirstIdSql = "SELECT substr(CERT_NUM,5,2) AS CARD FROM sys_user WHERE OBJID='" + author + "'  ";
                List<Map<String, Object>> id = dpService.select(isCanFirstIdSql);
                logger.debug("判断是否为报告署名人sql:" + isCanFirstIdSql);
                String card = String.valueOf(id.get(0).get("CARD"));
                if (!"05".equals(card)) {
                    Map<String, Object> rs = new HashedMap();
                    rs.put("statuscode", "500");
                    Map<String, Object> rsinfo = new HashedMap();
                    rsinfo.put("info", "第一作者必须为报告署名人!");
                    finnalRs.add(rs);
                    finnalRs.add(rsinfo);
                    return finnalRs;
                }
            }
        }
        /*
* 校验是否要检测分管权限
* */
        String getRptTypeCheckInfo = " SELECT URT.IS_INDU_MGR_CHECK, URT.IS_STK_MGR_CHECK\n" +
                "    FROM UF_RPT_TYPE URT LEFT JOIN SYS_DATA_BASE SDB ON URT.OBJID=SDB.OBJID\n" +
                "   WHERE URT.OBJID = '" + rpttype + "' AND SDB.IS_DELETE='0'";
        List<Map<String, Object>> checkInfo = dpService.select(getRptTypeCheckInfo);
        if (checkInfo.size() != 1) {
            throw new UnsupportedOperationException("报告分类id为:" + rpttype + "的分类数据存在冗余或已删除");
        }
        String IS_INDU_MGR_CHECK = String.valueOf(checkInfo.get(0).get("IS_INDU_MGR_CHECK")).equals("1") ? "1" : "0";
        String IS_STK_MGR_CHECK = String.valueOf(checkInfo.get(0).get("IS_STK_MGR_CHECK")).equals("1") ? "1" : "0";

/*
* 校验是否分管该公司股票
* */
        String induId = String.valueOf(rptInduRat.get(0).get("FK_INDU_ID"));
        ArrayList<String> secuList = new ArrayList<>();
        String induStr = "";
        Boolean induflag = true;
        Boolean secuflag = true;
        Boolean subsecuflag = true;
        if (rptSecRat.size() != 0 && "1".equals(IS_STK_MGR_CHECK)) {

            for (int i = 0; i < rptSecRat.size(); i++) {
                String stockName = "";
                subsecuflag = true;
                for (int k = 0; k < rptAuthor.size(); k++) {
                    Map<String, Object> rptAuthorMap = rptAuthor.get(k);
                    String author = String.valueOf(rptAuthorMap.get("FK_USER_ID"));
                    Map<String, Object> secMap = rptSecRat.get(i);
                    String secuid = String.valueOf(secMap.get("FK_SECU_ID"));
                    stockName = String.valueOf(secMap.get("STOCK_NAME"));
                    String checksecu = "SELECT COUNT(1) AS TOTAL\n" +
                            "          FROM UF_BAS_SECU_ASSIGN\n" +
                            "         WHERE FK_SECU_ID='" + secuid + "' AND IS_ASSIGNED='1' AND FK_USER_ID = '" + author + "'";
                    List<Map<String, Object>> rs = dpService.select(checksecu);
                    logger.debug("检测是否存在公司分管sql:" + checksecu);
                    String total = String.valueOf(rs.get(0).get("TOTAL"));
                    if ("0".equals(total)) {
                        subsecuflag = false;
                    } else {
                        subsecuflag = true;
                        break;
                    }
                }
                if (!subsecuflag) {
                    secuList.add(stockName);//未通过校验的股票名称
                    secuflag = false;
                }

            }
        }

        /*
* 校验是否分管该行业
* */
        if (rptInduRat.size() != 0 && !"".equals(induId) && "1".equals(IS_INDU_MGR_CHECK)) {
            outterInduLoop:
            for (int i = 0; i < rptExch.size(); i++) {
                for (int k = 0; k < rptAuthor.size(); k++) {
                    induflag = false;
                    Map<String, Object> rptAuthorMap = rptAuthor.get(k);
                    String author = String.valueOf(rptAuthorMap.get("FK_USER_ID"));
                    Map<String, Object> rptExchMap = rptExch.get(i);
                    String exchCode = String.valueOf(rptExchMap.get("EXCH_CODE"));
                    induStr = String.valueOf(rptInduRat.get(0).get("INDU_NAME"));
                    String checkIndu = "SELECT COUNT(1) AS TOTAL\n" +
                            "                  FROM UF_BAS_INDU_ASSIGN\n" +
                            "                 WHERE FK_USER_ID = '" + author + "'\n" +
                            "                 AND IS_ASSIGNED='1'  AND FK_INDU_ID = '" + induId + "'\n" +
                            "                   AND EXCH_TYPE = '" + exchCode + "'";
                    List<Map<String, Object>> rs = dpService.select(checkIndu);
                    String total = String.valueOf(rs.get(0).get("TOTAL"));
                    if (!"0".equals(total)) {
                        induflag = true;
                        break outterInduLoop;
                    }
                }
            }
        }
        if (induflag && secuflag) {
            Map<String, Object> rs = new HashedMap();
            rs.put("statuscode", "200");
            finnalRs.add(rs);
        } else {
            Map<String, Object> rs = new HashedMap();
            rs.put("statuscode", "500");
            finnalRs.add(rs);
            if (!induflag) {
                Map<String, Object> indurs = new HashedMap();
                indurs.put("info", "所有作者均未分管【" + induStr + "】");
                finnalRs.add(indurs);
            }
            if (!secuflag) {
                for (int i = 0; i < secuList.size(); i++) {
                    Map<String, Object> securs = new HashedMap();
                    securs.put("info", "所有作者均未分管股票【" + secuList.get(i) + "】");
                    finnalRs.add(securs);
                }
            }
        }
        return finnalRs;
    }

}
