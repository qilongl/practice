package com.lql.xml.XMLAnalyzer.converter;

import com.alibaba.fastjson.JSON;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.util.TypeUtil;
import com.lql.xml.db.DPService;
import com.lql.xml.inf.IConverter;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/12/29.
 */
public class BatchIsDeleteJsonStrConverter implements IConverter {
    private static Logger logger = LoggerFactory.getLogger(BatchIsDeleteJsonStrConverter.class);

    @Override
    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) {
        logger.debug("可用参数为:" + JSON.toJSONString(jsonObject));
        List<Map<String, Object>> list = TypeUtil.changeToListMap(dataSet);
        String jsonString = JSON.toJSONString(list);
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("jsonStr", jsonString);
        logger.debug("构建注销行程入参" + jsonobj.toString());
        return jsonobj;
    }
}
