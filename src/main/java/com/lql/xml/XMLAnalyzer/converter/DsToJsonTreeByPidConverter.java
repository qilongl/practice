//package com.lql.xml.XMLAnalyzer.converter;
//
//import com.alibaba.fastjson.JSON;
//import com.cbdcloud.beans.Action;
//import com.cbdcloud.db.DPService;
//import com.cbdcloud.inf.IConverter;
//import net.sf.json.JSONObject;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by deng on 2017/9/19.
// * 传入List<Map<String,Object>>结果集,通过PID列转换成JSONObject 树形的json对象
// */
//public class DsToJsonTreeByPidConverter implements IConverter {
//
//    @Override
//    public JSONObject exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) {
//        System.out.println(JSON.toJSON(jsonObject));
//        return null;
//    }
//
//    public static void main(String args[]) {
////        DsToJsonTreeByPidConverter converter=new DsToJsonTreeByPidConverter();
////        List<Map<String,Object>> list=new ArrayList<>();
////        List<Map<String,Object>> list2=new ArrayList<>();
////        converter.exec(list);
//    }
//}
