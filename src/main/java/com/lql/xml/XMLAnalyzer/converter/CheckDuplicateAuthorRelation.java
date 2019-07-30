package com.lql.xml.XMLAnalyzer.converter;

import com.alibaba.fastjson.JSON;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.db.DPService;
import com.lql.xml.inf.IConverter;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fu on 2018/02/06.
 */
public class CheckDuplicateAuthorRelation implements IConverter {
    private static Logger logger = LoggerFactory.getLogger(CheckDuplicateAuthorRelation.class);

    @Override
    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) {
        logger.debug("可用参数为:" + JSON.toJSONString(jsonObject));
        Boolean flag = false;
        ArrayList<Map> arrayList = (ArrayList) dataSet;
        ArrayList<Map> newlist = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            Map map = arrayList.get(i);
            String tmp_psn_acct = String.valueOf(map.get("TMP_PSN_ACCT"));
            String rpt_crt_psn = String.valueOf(map.get("RPT_CRT_PSN"));
            // String auth_typ = String.valueOf(map.get("AUTH_TYP"));
            // String dsp_order = String.valueOf(map.get("DSP_ORDER"));
            String getExsistSql = "SELECT COUNT(1) AS TOTAL FROM UF_RPT_AUTHOR_SIGNATURE UPAS\n" +
                    "LEFT JOIN SYS_DATA_BASE SDB ON UPAS.OBJID=SDB.OBJID\n" +
                    " WHERE  WRT_PSN='" + rpt_crt_psn + "' AND DSP_AUTH= '" + tmp_psn_acct + "'AND SDB.IS_DELETE='0'";
            List<Map<String, Object>> RS = dpService.select(getExsistSql);
            String total = String.valueOf(RS.get(0).get("TOTAL"));
            if (!"0".equals(total)) {
                String getcrtNameSql = "select objname from sys_user where objid='" + rpt_crt_psn + "' and is_delete='0' and is_close='0'";
                String getacctNameSql = "select objname from sys_user where objid='" + tmp_psn_acct + "' and is_delete='0' and is_close='0'";
                List<Map<String, Object>> crtlist = dpService.select(getcrtNameSql);
                List<Map<String, Object>> acctlist = dpService.select(getacctNameSql);
                String crtName = String.valueOf(crtlist.get(0).get("OBJNAME"));
                String acctName = String.valueOf(acctlist.get(0).get("OBJNAME"));
                HashMap<String, String> newMap = new HashMap<>();
                String info = "撰写人:" +"【"+crtName+"】与" + "署名分析师:" +"【"+acctName+"】"  + "的关系已存在";
                newMap.put("errorinfo", info);
                newlist.add(newMap);
                flag = true;
            }

        }
        if (flag) {
            HashMap<String, String> newMap = new HashMap<>();
            newMap.put("statuscode", "500");
            newlist.add(newMap);
        } else {
            HashMap<String, String> newMap = new HashMap<>();
            newMap.put("statuscode", "200");
            newlist.add(newMap);
        }
        return newlist;
    }

}
