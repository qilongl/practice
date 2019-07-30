//package com.lql.tools.XMLAnalyzer.service.actions;
//
//import com.cbdcloud.beans.Action;
//import com.cbdcloud.beans.UserException;
//import com.cbdcloud.xml.XMLAnalyzer.beans.BusiConfig;
//import com.cbdcloud.util.TypeUtil;
//import net.sf.json.JSONObject;
//
//import java.io.Serializable;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
//
///**
// * Created by lql on 2017/5/25.
// */
//public class LeftJoinAction implements Serializable {
//    private BusiConfig busiConfig;
//
//    public LeftJoinAction(BusiConfig busiConfig) {
//        this.busiConfig = busiConfig;
//    }
//
//    //--------------------数据源合并(可以是入参中到数据也可以是结果集)----------------------------------//
//    public List<Map<String, Object>> leftjoinCommand(Action cmd, JSONObject inParams) throws Exception {
//        List<Map<String, Object>> left = null;
//        try {
//            List<Map<String, Object>> right = null;
//            left = TypeUtil.changeToListMap(busiConfig.getParamFromInParams(cmd.getLeft(), inParams));
//            right = TypeUtil.changeToListMap(busiConfig.getParamFromInParams(cmd.getRight(), inParams));
//            if (left == null) left = TypeUtil.changeToListMap(busiConfig.getParamFromCmdResult(cmd.getLeft()));
//            if (right == null) right = TypeUtil.changeToListMap(busiConfig.getParamFromCmdResult(cmd.getRight()));
//            if (left == null) throw new NoSuchElementException("left 指定的数据源无法找到！");
//            if (right == null) throw new NoSuchElementException("right 指定的数据源无法找到！");
//            // 循环左侧数据源
//            for (int i = 0; i < left.size(); i++) {
//                Map leftMap = left.get(i);
//                String leftValue = leftMap.get(cmd.getOn()).toString();
//                boolean flag = false;
//                Map rightMap = null;
//                // 在右侧数据源中找到对应列中的数据
//                for (int j = 0; j < right.size(); j++) {
//                    rightMap = right.get(j);
//                    String rightValue = rightMap.get(cmd.getOn()).toString();
//                    if (leftValue.equalsIgnoreCase(rightValue)) {
//                        leftMap.putAll(rightMap);
//                        flag = true;
//                        break;
//                    }
//                }
//                // 如果没有找到,则设置空数据源
//                if (!flag) {
//                    Iterator iterator = rightMap.keySet().iterator();
//                    while (iterator.hasNext()) {
//                        String key = iterator.next().toString();
//                        leftMap.put(key, null);
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            throw new UserException(cmd.getId(),cmd.getErrorid(), "", ex);
//        }
//        return left;
//    }
//
//
//}
