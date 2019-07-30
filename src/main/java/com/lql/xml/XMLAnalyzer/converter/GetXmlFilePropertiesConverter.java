package com.lql.xml.XMLAnalyzer.converter;

import com.lql.util.StringHelper;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.util.TypeUtil;
import com.lql.xml.db.DPService;
import com.lql.xml.inf.IConverter;
import net.sf.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by json on 2018/4/12.
 */
public class GetXmlFilePropertiesConverter implements IConverter {
    /***
     * 定义请求表示的属性名称
     */
    private final String reqUrl = "reqUrl";
    private final String fileName = "fileName";
    private final String savePath = "relativeSavePath";
    private final String postFix = ".xml";

    @Override
    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) throws Exception {
        List<Map<String, Object>> list = TypeUtil.changeToListMap(dataSet);
        Map<String, Object> objMap = list.get(0);
        String url = StringHelper.toString(objMap.get(reqUrl));
        //xml文件存储的相对路径
        String relativeSavePath = url.substring(0, url.lastIndexOf("-")).replace("-", File.separator);
        //xml文件的名称
        String xmlFileName = url.substring(url.lastIndexOf("-") + 1, url.length()) + postFix;
        List result = new ArrayList<>();
        Map map = new HashMap<>();
        map.put(fileName, xmlFileName);
        map.put(savePath, relativeSavePath);
        result.add(map);
        return result;
    }
}
