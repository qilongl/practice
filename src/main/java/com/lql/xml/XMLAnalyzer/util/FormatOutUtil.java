package com.lql.xml.XMLAnalyzer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/7/31.
 */
public class FormatOutUtil {
    private static Logger logger = LoggerFactory.getLogger(FormatOutUtil.class);

    public static String out(String id, List<Map<String, Object>> list) {
        /****
         * 1.取出所有的列集合
         * 2.对应取出所有的列的值
         */
        if (!logger.isDebugEnabled()) {
            return "over";
        }
        logger.debug("==================操作:" + id+"===================");
        List<String> colNameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            if (i == 0) {
                StringBuffer colsBuffer = new StringBuffer();
                Iterator iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next().toString();
                    colNameList.add(key);
                    colsBuffer.append(key + "\t");
                }
                logger.debug(colsBuffer.toString());
            }
            StringBuffer valueBuffer = new StringBuffer();
            for (int j = 0; j < colNameList.size(); j++) {
                String colName = colNameList.get(j);
                Object value = map.get(colName);
                valueBuffer.append(value + "\t");
            }
            logger.debug(valueBuffer.toString());
        }
        logger.debug("==============OVER: 共"+list.size()+"条结果集============");
        return "over";
    }

    public static void main(String args[]) {

    }
}
