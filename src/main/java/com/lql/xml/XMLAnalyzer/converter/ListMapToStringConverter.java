//package com.lql.xml.XMLAnalyzer.converter;
//
//import com.alibaba.fastjson.JSON;
//import com.cbdcloud.beans.Action;
//import com.cbdcloud.db.DPService;
//import com.cbdcloud.inf.IConverter;
//import com.cbdcloud.util.JsonValueFilter;
//import com.cbdcloud.util.TypeUtil;
//import net.sf.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * listMap
// * [{},{},....]
// * 类型转成
// * <p>
// * [{"VALUE":"[{},{},......]"}]
// * Created by deng on 2017/12/6.
// */
//public class ListMapToStringConverter implements IConverter {
//    private final static String columnName = "VALUE";
//
//    @Override
//    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) {
//        if (!TypeUtil.isListMap(dataSet))
//            throw new UnsupportedOperationException("入参不是List<Map>类型!");
//        List<Map<String, Object>> result = TypeUtil.changeToListMap(dataSet);
//        String columnValue = JSON.toJSONString(result, JsonValueFilter.changeNullToString());
//        List list = new ArrayList<>();
//        Map map = new HashMap<>();
//        map.put(columnName, columnValue);
//        list.add(map);
//        return list;
//    }
//}
