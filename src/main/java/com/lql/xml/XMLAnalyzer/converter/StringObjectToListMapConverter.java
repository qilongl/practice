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
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by deng on 2017/10/12.
// * {"startWf":"{\"msg\":\"流程实例创建成功\",\"result\":\"db0734879bfd4f93a59c6ea41b149718\",\"statusCode\":0}"}
// * 转换成
// * {"startWf":[{"msg":"流程实例创建成功","result":"db0734879bfd4f93a59c6ea41b149718","statusCode":0}]}
// */
//public class StringObjectToListMapConverter implements IConverter {
//    private static Logger logger = LoggerFactory.getLogger(StringToJsonConverter.class);
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
//        com.alibaba.fastjson.JSONArray jsonArray;
//        List<Map> list = new ArrayList<>();
//        int jsonType = JsonValueFilter.getJsonType(content);
//        /***
//         * 转换成json对象,然后存放到List<map>集合中
//         */
//        if (jsonType == JsonValueFilter.JSON_OBJECT) {
//            jsonObj = JSON.parseObject(content);
//            list.add(jsonObj);
//        } else if (jsonType == JsonValueFilter.JSON_ARRAY) {
//            jsonArray = JSON.parseArray(content);
//            for (int i = 0; i < jsonArray.size(); i++) {
//                jsonObj = jsonArray.getJSONObject(i);
//                list.add(jsonObj);
//            }
//        } else {
//            throw new UnsupportedOperationException(content + "json格式错误!");
//        }
//        return list;
//    }
//
//}
