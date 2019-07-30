//package com.lql.tools.XMLAnalyzer.service.actions;
//
//import com.cbdcloud.beans.Action;
//import com.cbdcloud.beans.UserException;
//import com.cbdcloud.xml.XMLAnalyzer.beans.BusiConfig;
//import com.cbdcloud.util.TypeUtil;
//
//import java.io.Serializable;
//import java.util.*;
//
///**
// * Created by lql on 2017/8/15.
// */
//public class MixAction implements Serializable {
//    private BusiConfig busiConfig;
//
//    public MixAction(BusiConfig busiConfig) {
//        this.busiConfig = busiConfig;
//    }
//
//
//    public List<Map<String, Object>> mixCommand(Action cmd) throws Exception {
//        String dataset = cmd.getDataset();
//        String mixcol = cmd.getMixcol();
//        List<Map<String, Object>> lastResult = null;
//        try {
//            String[] datasetList = dataset.split(",");
//            for (int i = 0; i < datasetList.length; i++) {
//                String datasetName = datasetList[i];
//                List<Map<String, Object>> result = TypeUtil.changeToListMap(busiConfig.getParamFromCmdResult(datasetName));
//                if (i == 0) {
//                    lastResult = result;
//                    continue;
//                }
//                lastResult = mixDataSet(lastResult, result, mixcol);
//            }
//        } catch (Exception ex) {
//            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
//        }
//        return lastResult;
//    }
//
//    public List<Map<String, Object>> mixDataSet(List<Map<String, Object>> left, List<Map<String, Object>> right, String mixcol) {
//        List<Map<String, Object>> result = new ArrayList<>();
//        for (int i = 0; i < left.size(); i++) {
//            Map leftMap = left.get(i);
//            Object leftObject = leftMap.get(mixcol);
//            for (int j = 0; j < right.size(); j++) {
//                Map rightMap = right.get(j);
//                Object rightObject = rightMap.get(mixcol);
//                if (isEqual(leftObject, rightObject)) {
//                    Map map = new HashMap<>();
//                    map.put(mixcol, rightMap.get(mixcol));
//                    result.add(map);
//                }
//            }
//        }
//        return result;
//    }
//
//    public boolean isEqual(Object o1, Object o2) {
//        if (o1 instanceof String) {
//            if (String.valueOf(o1).equals(String.valueOf(o2)))
//                return true;
//            else
//                return false;
//        } else if (o1 instanceof Integer) {
//            if (Integer.parseInt(o1.toString()) == Integer.parseInt(o2.toString()))
//                return true;
//            else
//                return false;
//        } else if (o1 instanceof Double) {
//            if (Double.parseDouble(o1.toString()) == Double.parseDouble(o2.toString()))
//                return true;
//            else
//                return false;
//        } else if (Float.parseFloat(o1.toString()) == Float.parseFloat(o2.toString())) {
//            if (Float.parseFloat(o1.toString()) == (Float.parseFloat(o2.toString())))
//                return true;
//            else
//                return false;
//        } else if (o1 instanceof Date) {
//            if (((Date) o1).getTime() == ((Date) o2).getTime())
//                return true;
//            else
//                return false;
//        } else if (o1 instanceof Boolean) {
//            if (((Boolean) o1).booleanValue() == ((Boolean) o2).booleanValue())
//                return true;
//            else
//                return false;
//        } else {
//            throw new UnsupportedOperationException("出现了本框架暂时不支持的数据类型比较内容:" + o1.toString());
//        }
//
//    }
//
//    public static void main(String args[]) {
//        List<Map<String, Object>> right = new ArrayList<>();
//        Map map1 = new HashMap();
//        map1.put("a", "1");
//        Map map2 = new HashMap();
//        map2.put("a", "2");
//        Map map3 = new HashMap();
//        map3.put("a", "3");
//        right.add(map1);
//        right.add(map2);
//        right.add(map3);
//
//        List<Map<String, Object>> left = new ArrayList<>();
//        Map map4 = new HashMap();
//        map4.put("a", "1");
//        Map map5 = new HashMap();
//        map5.put("a", "3");
//        Map map6 = new HashMap();
//        map6.put("a", "4");
//        left.add(map4);
//        left.add(map5);
//        left.add(map6);
//        MixAction mixAction = new MixAction(null);
//        List<Map<String, Object>> result = mixAction.mixDataSet(left, right, "a");
//        System.out.println(result.size());
//    }
//}
