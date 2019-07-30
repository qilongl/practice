package com.lql.xml.XMLAnalyzer.converter;

import com.alibaba.fastjson.JSON;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.db.DPService;
import com.lql.xml.inf.IConverter;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2018/1/29.
 * 传入对应的业务数据,组装JsonParams参数
 */
public class CreateJsonParams implements IConverter {
    private static Logger logger = LoggerFactory.getLogger(CreateJsonParams.class);

    @Override
    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) {
        logger.debug("可用参数为:" + JSON.toJSONString(jsonObject));
        //取到入参数据集

        ArrayList<Map> arrayList = (ArrayList) dataSet;
        for (int i = 0; i < arrayList.size(); i++) {
            Map map = arrayList.get(i);
            String objid = String.valueOf(map.get("objId"));

            String jsonParams = "{\n" +
                    "    \"Functions\": [\n" +
                    "        {\n" +
                    "            \"FunctionName\": \"xxxxxx\",\n" +
                    "            \"ModuleName\": \"xxxx-xxxxx\",\n" +
                    "            \"Parameters\": {\n" +
                    "                \"flag\": {\n" +
                    "                    \"isupdate\": \"false\"\n" +
                    "                },\n" +
                    "                \"wfData\": {\n" +
                    "                    \"dbId\": \"" + objid + "\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            map.put("jsonParams", jsonParams);
        }
        logger.debug("组合jsonParams后的流程入参" + arrayList.toString());
        return arrayList;
    }

}
