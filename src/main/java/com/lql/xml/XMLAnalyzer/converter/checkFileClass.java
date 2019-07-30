package com.lql.xml.XMLAnalyzer.converter;

import com.lql.xml.XMLAnalyzer.service.impl.BusiConfigCache;
import com.lql.xml.db.DPFactory;
import com.lql.xml.db.DPService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by fu on 2018/3/14.
 * 检测6个月内调研对象的调研资料是否必填(只针对上市公司)
 */
public class checkFileClass {

    private static Logger logger = LoggerFactory.getLogger(checkFileClass.class);

    public List<Map<String, Object>> check(List<Map<String, Object>> persons, List<Map<String, Object>> orgs) throws Exception {
        DPService dpService = DPFactory.createDPService(BusiConfigCache.ctx);
        List<Map<String, Object>> finnalRs = new ArrayList<>();//结果集

        if (orgs.size() == 0) {
            Map<String, Object> rs = new HashedMap();
            rs.put("statuscode", "200");
            finnalRs.add(rs);
            return finnalRs;
        }
        Set<String> sets = new HashSet();//关联属性
        for (int i = 0; i < orgs.size(); i++) {
            Map<String, Object> orgMap = orgs.get(i);
            String ref = String.valueOf(orgMap.get("ref"));
            sets.add(ref);
        }

        /***
         * 多个行程数据拆分归类
         */
        HashMap<String, List<String>> orgMap = new HashMap<>();
        HashMap<String, List<String>> personMap = new HashMap<>();
        for (String set : sets) {
            ArrayList<String> orglist = new ArrayList<>();
            for (int i = 0; i < orgs.size(); i++) {
                String ref = String.valueOf(orgs.get(i).get("ref"));
                if (set.toString().equals(ref)) {
                    String orgId = String.valueOf(orgs.get(i).get("orgId"));
                    orglist.add(orgId);
                    orgMap.put(ref, orglist);
                }
            }
            ArrayList<String> personlist = new ArrayList<>();
            for (int j = 0; j < persons.size(); j++) {
                String ref = String.valueOf(persons.get(j).get("ref"));
                if (set.toString().equals(ref)) {
                    String personId = String.valueOf(persons.get(j).get("personId"));
                    personlist.add(personId);
                    personMap.put(ref, personlist);
                }
            }
        }


        /***
         * 遍历查询数据库中是否存在满足条件的结果
         */
        Set<String> needAttachOrg = new HashSet();
        Iterator<String> iterator = sets.iterator();
        while (iterator.hasNext()) {
            String ref = iterator.next();
            List<String> orgList = orgMap.get(ref);
            List<String> personList = personMap.get(ref);
            for (String org : orgList) {
                for (String person : personList) {
                    String checkSql = "SELECT COUNT(*) AS ISNEEDUPLOAD\n" +
                            "  FROM UF_SCHE US\n" +
                            "  LEFT JOIN SYS_DATA_BASE SDB\n" +
                            "    ON US.OBJID = SDB.OBJID\n" +
                            "  LEFT JOIN UF_SCHE_SERVICE_PSN USSP\n" +
                            "    ON US.OBJID = USSP.FK_SCHE_ID\n" +
                            "   AND USSP.IS_DELETE = '0'\n" +
                            "  LEFT JOIN UF_SCHE_DETAIL USD\n" +
                            "    ON USD.FK_SCHE_ID = US.OBJID\n" +
                            "   AND USD.IS_DELETE = '0'\n" +
                            " WHERE USD.SCHE_DETAI_TYPE = '1'\n" +
                            "   AND SDB.STATUS = 3\n" +
                            "   AND US.FK_SCHE_TYPE_ID = 'EE52CF83058A497788B57932D432E5D8'\n" +
                            "   AND SDB.WF_CRT_DT > ADD_MONTHS(SYSDATE, -6)\n" +
                            "   AND USSP.FK_USER_ID = ?" +
                            "   AND USD.FK_CRMORG_ID = ?" +
                            "   AND SDB.IS_DELETE='0'";
                    ArrayList<String> params = new ArrayList<>();
                    params.add(person);
                    params.add(org);
                    List<Map<String, Object>> sqlResult = dpService.select(checkSql, params);
                    String rs = String.valueOf(sqlResult.get(0).get("ISNEEDUPLOAD"));
                      /*对非上市公司做过滤*/
                    String isListedCompany = " SELECT 1\n" +
                            "   FROM DUAL\n" +
                            "  WHERE EXISTS (SELECT 1\n" +
                            "           FROM UF_CRM_ORG UCO\n" +
                            "           LEFT JOIN SYS_DATA_BASE SDB\n" +
                            "             ON UCO.OBJID = SDB.OBJID\n" +
                            "           LEFT JOIN UF_CRM_ORG_ORGTYPE UCOO\n" +
                            "             ON UCOO.FK_ORG_BASE_INFO = UCO.OBJID\n" +
                            "          WHERE UCOO.ORGTYPE = '14'\n" +
                            "            AND SDB.IS_DELETE = '0'\n" +
                            "           AND uco.objid='" + org + "')";
                    List<Map<String, Object>> sqlOrg = dpService.select(isListedCompany);
                    if ("0".equals(rs) && sqlOrg.size() == 1) {
                        needAttachOrg.add(org);
                    }
                }
            }
        }
        if (needAttachOrg.size() == 0) {
            Map<String, Object> rs = new HashedMap();
            rs.put("statuscode", "200");
            finnalRs.add(rs);
            return finnalRs;
        } else {
            for (String Org : needAttachOrg) {
                for (int j = 0; j < orgs.size(); j++) {
                    if (orgs.get(j).get("orgId").equals(Org)) {
                        HashMap<String, Object> orgNameMap = new HashMap<>();
                        String orgName = String.valueOf(orgs.get(j).get("orgName"));
                        orgNameMap.put("orgName", orgName);
                        finnalRs.add(orgNameMap);
                        break;
                    }
                }
                Map<String, Object> rs = new HashedMap();
                rs.put("statuscode", "500");
                finnalRs.add(rs);
            }
            return finnalRs;
        }
    }
}

