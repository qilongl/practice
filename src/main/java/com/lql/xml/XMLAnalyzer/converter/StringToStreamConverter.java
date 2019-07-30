package com.lql.xml.XMLAnalyzer.converter;

import com.lql.util.StringHelper;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.db.DPService;
import com.lql.xml.inf.IConverter;
import net.sf.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by json on 2018/4/10.
 * 字符串转成stream
 */
public class StringToStreamConverter implements IConverter {
    private final String content = "CONTENT";
    private final String source = "SOURCE";
    private final String xmlTitle = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    @Override
    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) throws Exception {
        //取出stream
        List<Map> result = (List) dataSet;
        Map<String, Object> objMap = result.get(0);
        String xmlContent = StringHelper.toString(objMap.get(content));
        if (!xmlContent.startsWith(xmlTitle)){
            xmlContent = xmlTitle.concat(xmlContent);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        Map map = new HashMap();
        map.put(source, xmlContent.getBytes("utf-8"));
        list.add(map);
        return list;
    }
}
