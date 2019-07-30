//package com.lql.xml.XMLAnalyzer.service.actions;
//
//import com.lql.xml.XMLAnalyzer.beans.Action;
//import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
//import com.lql.xml.XMLAnalyzer.beans.UserException;
//import com.lql.xml.XMLAnalyzer.util.TypeUtil;
//import org.springframework.util.LinkedCaseInsensitiveMap;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by lql on 2017/8/31.
// */
//public class CrossAction implements Serializable {
//    private BusiConfig busiConfig;
//
//    public CrossAction(BusiConfig busiConfig) {
//        this.busiConfig = busiConfig;
//    }
//
//    /***
//     * 交叉命令
//     *
//     * @param cmd
//     * @return
//     * @throws Exception
//     */
//    public List<Map<String, Object>> crossCommand(Action cmd) throws Exception {
//        String dataset = cmd.getDataset();
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
//                lastResult = cross2DataSet(lastResult, result);
//            }
//        } catch (Exception ex) {
//            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
//        }
//        return lastResult;
//    }
//
//    /***
//     * 两个结果集交叉
//     *
//     * @param left
//     * @param right
//     * @return
//     */
//    public List<Map<String, Object>> cross2DataSet(List<Map<String, Object>> left, List<Map<String, Object>> right) {
//        List<Map<String, Object>> result = new ArrayList<>();
//        for (int i = 0; i < left.size(); i++) {
//            Map<String, Object> leftMap = left.get(i);
//            for (int j = 0; j < right.size(); j++) {
//                Map<String, Object> rightMap = right.get(j);
//                LinkedCaseInsensitiveMap cloneLeftMap = ((LinkedCaseInsensitiveMap) leftMap).clone();
//                cloneLeftMap.putAll(rightMap);
//                result.add(cloneLeftMap);
//            }
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap();
//        map.put("1", "a");
//        map.put("2", "a");
//        HashMap hashMap = (HashMap) ((HashMap) map).clone();
//        hashMap.put("3", "c");
//        System.out.println(hashMap);
//    }
//}
