package com.lql.xml.XMLAnalyzer.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fu on 2017/12/30.
 * 对用户数据权限进行检查
 */
public class CheckPremData {


    private final static String OPTVAL = "OPTVAL";
    private static Logger logger = LoggerFactory.getLogger(CheckPremData.class);

    public List<Map<String, Object>> check(List<Map<String, Object>> PremResult, List<Map<String, Object>> type) throws Exception {
        List<Map<String, Object>> rs = new ArrayList<>();
        HashMap<String, Object> codemap = new HashMap<>();
        String typecode = (String) type.get(0).get("type");
        for (int i = 0; i < PremResult.size(); i++) {
            Map<String, Object> map = PremResult.get(i);
            String optval = map.get(OPTVAL).toString();
            if (typecode.equals(optval)) {
                codemap.put("RESULT", "1");
                break;
            }
        }
        if (codemap.size() == 0) {
            codemap.put("RESULT", "0");
        }
        rs.add(codemap);
        return rs;
    }

}
