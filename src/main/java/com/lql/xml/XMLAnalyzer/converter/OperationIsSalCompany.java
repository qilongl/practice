package com.lql.xml.XMLAnalyzer.converter;

import com.lql.xml.XMLAnalyzer.service.impl.BusiConfigCache;
import com.lql.xml.db.DPFactory;
import com.lql.xml.db.DPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * Created by fu on 2018/1/25.
 * 检测行程是否销售陪同
 */
public class OperationIsSalCompany {

    private static Logger logger = LoggerFactory.getLogger(OperationIsSalCompany.class);

    public List<Map<String, Object>> operation(List<Map<String, Object>> schedulePsnvar, List<Map<String, Object>> operationIsSal) throws Exception {
        DPService dpService = DPFactory.createDPService(BusiConfigCache.ctx);
        HashSet<String> set = new HashSet<>();
        HashMap<String, ArrayList<String>> mapPsn = new HashMap<>();
        for (int i = 0; i < operationIsSal.size(); i++) {
            Map<String, Object> map = operationIsSal.get(i);
            String refSchedule = (String) map.get("refSchedule");
            set.add(refSchedule);
        }
        for (String ref : set) {
            ArrayList<String> psnList = new ArrayList<>();
            for (int i = 0; i < schedulePsnvar.size(); i++) {
                Map<String, Object> map = schedulePsnvar.get(i);
                String refSchedule = (String) map.get("refSchedule");
                if (ref.equals(refSchedule)) {
                    String objid = (String) map.get("objid");
                    psnList.add(objid);
                }
            }
            mapPsn.put(ref, psnList);
        }
        for (String refPsn : set) {
            ArrayList<String> oneSchepsnList = mapPsn.get(refPsn);
            for (int i = 0; i < oneSchepsnList.size(); i++) {
                String id = oneSchepsnList.get(i);
                String checkIsSal = "SELECT COUNT(1) AS issal FROM sys_user WHERE objid='"+id+"' AND objtype='11'\n";
                List<Map<String, Object>> sizelist = dpService.select(checkIsSal);
                String issal =String.valueOf(sizelist.get(0).get("issal"));
                if (!"0".equals(issal)) {
                    for (int j = 0; j < operationIsSal.size(); j++) {
                        Map<String, Object> scheMap = operationIsSal.get(j);
                        String refSchedule = (String) scheMap.get("refSchedule");
                        if (refPsn.equals(refSchedule)) {
                            scheMap.put("isSalcompany", "1");
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return operationIsSal;
    }

}
