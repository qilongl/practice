//package com.lql.xml.XMLAnalyzer.converter;
//
//import com.alibaba.fastjson.JSON;
//import com.cbdcloud.beans.Action;
//import com.cbdcloud.db.DPService;
//import com.cbdcloud.inf.IConverter;
//import com.cbdcloud.util.JsonValueFilter;
//import net.sf.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by deng on 2017/10/12.
// * {"startWf":"{\"msg\":\"流程实例创建成功\",\"result\":["db0734879bfd4f93a59c6ea41b149718","db0734879bfd4f93a59c6ea41b149718"],\"statusCode\":0}"}
// * 转换成
// * {"startWf":[{"result":"db0734879bfd4f93a59c6ea41b149718"},{"result":"db0734879bfd4f93a59c6ea41b149718"}]}
// */
//public class RrResultToListMapConverter implements IConverter {
//    private static Logger logger = LoggerFactory.getLogger(StringToJsonConverter.class);
//    private static final String columnName = "result";
//
//    @Override
//    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) {
//        logger.debug("可用参数为:" + JSON.toJSONString(jsonObject));
//        //取到入参数据集
//        String content = (String) dataSet;
//        /***
//         * 取到结果集字符串
//         */
//        com.alibaba.fastjson.JSONObject jsonObj;
//        List<Map<String, Object>> list = new ArrayList<>();
//        int jsonType = JsonValueFilter.getJsonType(content);
//        /***
//         * 转换成json对象,然后存放到List<map>集合中
//         */
//        try {
//            if (jsonType == JsonValueFilter.JSON_OBJECT) {
//                jsonObj = JSON.parseObject(content);
//                if (jsonObj.containsKey(columnName)) {
//                    String result = jsonObj.getString(columnName);
//                    if (result.startsWith("[")) {
//                        String str = result.substring(1, result.length() - 1);
//                        String[] ids = str.split(",");
//                        for (int i = 0; i < ids.length; i++) {
//                            String id = ids[i].replaceAll("\"", "");
//                            Map map = new HashMap<>();
//                            map.put(columnName, id);
//                            list.add(map);
//                        }
//                    } else {
//                        Map map = new HashMap<>();
//                        map.put(columnName, result);
//                        list.add(map);
//                    }
//                } else {
//                    throw new UnsupportedOperationException(jsonObj.toString() + "中不包含result属性!");
//                }
//            } else {
//                throw new UnsupportedOperationException(content + "不是期望的JSONObject格式!");
//            }
//        } catch (Exception ex) {
//            throw new UnsupportedOperationException(getClass().getName() + "转换异常!", ex);
//        }
//        return list;
//    }
//
//}
