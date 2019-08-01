//package com.lql.xml.XMLAnalyzer.converter;
//
//import com.alibaba.fastjson.JSON;
//import com.cbdcloud.beans.Action;
//import com.cbdcloud.db.DPService;
//import com.cbdcloud.inf.IConverter;
//import com.cbdcloud.util.StringHelper;
//import net.sf.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by fu on 2017/12/27.
// * 对新增行程的id进行提前赋值
// *
// */
//public class InsertGuid implements IConverter {
//    private static Logger logger = LoggerFactory.getLogger(StringToJsonConverter.class);
//
//    @Override
//    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) {
//        logger.debug("可用参数为:" + JSON.toJSONString(jsonObject));
//        //取到入参数据集
//
//            ArrayList<Map> arrayList = (ArrayList) dataSet;
//            ArrayList<Map> newlist = new ArrayList<>();
//                for(int i=0;i<arrayList.size();i++){
//                    Map map = arrayList.get(i);
//                    if("".equals(map.get("id"))){
//                        map.put("id", StringHelper.getUUID());
//                    }
//                }
//        return arrayList;
//    }
//
//}