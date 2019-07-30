//package com.lql.xml.XMLAnalyzer.converter;
//
//import com.alibaba.fastjson.JSON;
//import com.lql.util.JsonValueFilter;
//import com.lql.util.StringHelper;
//import com.lql.xml.XMLAnalyzer.beans.Action;
//import com.lql.xml.XMLAnalyzer.util.TypeUtil;
//import com.lql.xml.db.DPService;
//import com.lql.xml.inf.IConverter;
//import net.sf.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by deng on 2017/9/22.
// * 把传入数据集的第一个行,VALUE列转成json对象返回
// * ===============================================
// * VALUE
// * {"age":"11","usernam":"jack"....}
// * 把 list<Map> 类型数据的第一行value列 转成  jsonObject 或者 jsonArray
// */
//public class StringToJsonConverter implements IConverter {
//
//    private static Logger logger = LoggerFactory.getLogger(StringToJsonConverter.class);
//
//    private static String columnName = "VALUE";
//
//    @Override
//    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) {
//        logger.debug("可用参数为:" + JSON.toJSONString(jsonObject));
//        //取到入参数据集
//        List<Map<String, Object>> list = TypeUtil.changeToListMap(dataSet);
//        if (list.size() != 1)
//            throw new UnsupportedOperationException("StringToJsonConverter 需要的入参结果集大小为1,实际为:" + list.size());
//        Map map = list.get(0);
//        //设置了要转换的列名
//        if (StringHelper.isNotNull(cmd.getColumnname()))
//            columnName = cmd.getColumnname();
//        String value = map.get(columnName).toString();
//        Object result;
//        int jsonType = JsonValueFilter.getJsonType(value);
//        if (jsonType == JsonValueFilter.JSON_OBJECT) {
//            result = JSON.parseObject(value);
//        } else if (jsonType == JsonValueFilter.JSON_ARRAY) {
//            result = JSON.parseArray(value);
//        } else {
//            throw new UnsupportedOperationException(value + "json格式错误!");
//        }
//        return result;
//    }
//
//}
