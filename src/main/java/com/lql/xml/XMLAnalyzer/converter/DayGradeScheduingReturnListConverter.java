//package com.lql.xml.XMLAnalyzer.converter;
//
//import com.cbdcloud.beans.Action;
//import com.cbdcloud.db.DPService;
//import com.cbdcloud.inf.IConverter;
//import net.sf.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * desc:日历档期排班转换器
// *
// * @author haiyangp
// *         date:   2018/1/16
// */
//public class DayGradeScheduingReturnListConverter implements IConverter {
//    private Logger logger = LoggerFactory.getLogger(DayGradeScheduingReturnListConverter.class);
//    /**
//     * 依次为:上午、中午、下午第一场、下午第二场、下午第三场
//     */
//    private String[] timeArray = {"10", "11", "12", "13", "14"};
//
//
//    @Override
//    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) {
//        if (!(dataSet instanceof List)) {
//            throw new UnsupportedOperationException("被转换的数据不为list,不支持");
//        }
//        //结果集合
//        List<Map> resultList = new ArrayList<>();
//
//
//        //传入的是这个用户这一周的数据
//        List<Map<String, String>> dataList = (List<Map<String, String>>) dataSet;
//        if (dataList.isEmpty()) {
//            logger.debug("这个用户这一周都无任何数据");
//            return resultList;
//        }
//        //获取出这一周从周一到周5的每一天的字符串
//        String[] thisWeekDay = getThisWeekDayStr(dataList.get(0));
//        if (logger.isDebugEnabled()) {
//            for (String dayItem : thisWeekDay) {
//                logger.debug("thisWeekDay:{}", dayItem);
//            }
//        }
//
//        boolean[][] childHasProcessArray = new boolean[5][5];
//        //I为星期,J为场次
//        for (int j = 0; j < 5; j++) {
//            for (int i = 0; i < 5; i++) {
//                boolean hasFound = false;
//                for (Map<String, String> mapItem : dataList) {
//                    if (mapItem.get("GRADEDAY") != null && mapItem.get("GRADETIME") != null &&
//                            mapItem.get("GRADEDAY").equals(thisWeekDay[i]) && mapItem.get("GRADETIME").equals(timeArray[j])) {
//                        logger.debug("匹配到有效数据 i:{} j:{}", i, j);
//                        //这个时间点有数据
//                        buildAndaddResultList(i, j, childHasProcessArray, resultList, mapItem, true);
//                        hasFound = true;
//                    } else {
//                        logger.trace("未匹配到有效数据 i:{} j:{}", i, j);
//                    }
//                }
//                if (!hasFound) {
//                    Map nullMap = new HashMap(0);
//                    logger.debug("未匹配到有效数据 i:{} j:{}  NEW一个空点", i, j);
//                    //遍历完了都还没有搜索到，刚需要NEW出一个空的数据点
//                    //这个时间点无数据
//                    buildAndaddResultList(i, j, childHasProcessArray, resultList, nullMap, false);
//                }
//            }
//        }
//        return resultList;
//    }
//
//    /**
//     * 构造并添加结点到list中
//     *
//     * @param isShow 是否展示
//     */
//    private void buildAndaddResultList(int i, int j, boolean[][] childHasProcessArray, List<Map> resultList, Map mapItem, boolean isShow) {
//        mapItem.put("isShow", isShow + "");
//        if (isShow) {
//            if (childHasProcessArray[i][j]) {
//                //已经处理过了,获取出resultList的最后一个节点，往里面插入数据
//                Map lastMap = resultList.get(resultList.size() - 1);
//                //map里放一个list往list中放数据
//                ArrayList childArrayList = (ArrayList) lastMap.get("userGrade");
//                Map tempMap = new HashMap(mapItem);
//                childArrayList.add(tempMap);
//            } else {
//                //先把找到的这个节点放进去
//                //再放一个list进去，并把刚刚节点数据放进去
//                List childArrayList = new ArrayList();
//                Map tempMap = new HashMap(mapItem);
//                childArrayList.add(tempMap);
//                mapItem.put("userGrade", childArrayList);
//                resultList.add(mapItem);
//                //没有处理过，标记为已处理
//                childHasProcessArray[i][j] = true;
//            }
//        } else {
//            //这个节点一直没有数据，进行处理
//            resultList.add(mapItem);
//        }
//    }
//
//
//    /**
//     * 获取出这个星期这周的5天
//     */
//    private String[] getThisWeekDayStr(Map<String, String> valueMap) {
//        String[] thisWeekDayArray = new String[5];
//        //先获取出数据中的一天
//        String currentDayStr = valueMap.get("GRADEDAY");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date currentDate = null;
//        try {
//            currentDate = format.parse(currentDayStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            logger.error("获取指定时间的星期失败，日期转换出错了!");
//            throw new RuntimeException("获取指定时间的星期失败，日期转换出错了!");
//        }
//        Calendar instance = Calendar.getInstance();
//        instance.setTime(currentDate);
//        // 调整到周1
//        instance.set(Calendar.DAY_OF_WEEK, 2);
//        for (int i = 0; i < 5; i++) {
//            thisWeekDayArray[i] = format.format(instance.getTime());
//            instance.add(Calendar.DAY_OF_WEEK, 1);
//        }
//        return thisWeekDayArray;
//    }
//}
