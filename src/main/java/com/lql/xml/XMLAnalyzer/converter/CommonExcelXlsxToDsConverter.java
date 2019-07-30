//package com.lql.xml.XMLAnalyzer.converter;
//
//import com.cbdcloud.util.ReadExcelHelper;
//import com.cbdcloud.util.StringHelper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//
///**
// * Created by deng on 2017/9/20.
// */
//public class CommonExcelXlsxToDsConverter {
//    //入参中路径的key
//    public static final String PATH = "PATH";
//    public static final String VALUE = "value";
//    private static Logger logger = LoggerFactory.getLogger(CommonExcelXlsxToDsConverter.class);
//
//    public List<Map<String, Object>> read(List<Map<String, Object>> filePath, List<Map<String, Object>> sheetName, List<Map<String, Object>> columnConfig, List<Map<String, Object>> startRowContent, List<Map<String, Object>> endRowContent) throws Exception {
//        /***
//         * 取出参数
//         */
//        Map<String, Object> param = filePath.get(0);
//        String path = param.get(PATH).toString();
//        String startContent = StringHelper.toString(startRowContent.get(0).get(VALUE));
//        String endContent = StringHelper.toString(endRowContent.get(0).get(VALUE));
//        param = sheetName.get(0);
//        String sheetname = param.get(VALUE).toString();
//        param = columnConfig.get(0);
//        /***
//         * 构建新的列头columnMap,方便读取
//         */
//        Map columnMap = new HashMap<>();
//        List<String> columnNameList = new ArrayList<>();
//        Iterator it = param.keySet().iterator();
//        while (it.hasNext()) {
//            String key = it.next().toString();
//            String value = param.get(key).toString();
//            if (columnMap.containsKey(value))
//                throw new UnsupportedOperationException("excel的列配置参数错误,出现重复的列:" + value);
//            columnMap.put(value, key);
//            columnNameList.add(value);
//        }
//        /***
//         * 读取excel文件
//         */
//        ReadExcelHelper helper = new ReadExcelHelper();
//        Map<String, String> mapResult = helper.read(path, sheetname);
//        Calendar start = Calendar.getInstance();
//        List<Map<String, Object>> result = filterData(startContent, endContent, columnMap, mapResult);
//        Calendar end = Calendar.getInstance();
//        logger.debug("构建数据集总数:" + result.size() + ",耗时:" + (end.getTimeInMillis() - start.getTimeInMillis()) / (1000) + "s");
//        return result;
//    }
//
//    /***
//     * 从整体文档列表中截取startContent行开始,以endContent结束的列表
//     *
//     * @param startContent
//     * @param endContent
//     * @param colsMap
//     * @param dataDic
//     * @return
//     */
//    private List<Map<String, Object>> filterData(String startContent, String endContent, Map<String, String> colsMap, Map<String, String> dataDic) {
//        boolean isSave = false;
//        boolean breakFlag = false;
//        int saveNum = 0;
//        List<Map<String, Object>> result = new ArrayList<>();
//        for (int rowIndex = 1; rowIndex < dataDic.size(); rowIndex++) {
//            Map<String, Object> map = new HashMap<>();
//            Iterator iterator = colsMap.keySet().iterator();
//            while (iterator.hasNext()) {
//                //excel的列号
//                String dataColumn = StringHelper.toString(iterator.next());
//                //配置新的列号
//                String newColumn = colsMap.get(dataColumn);
//                //字典中的key
//                String dicKey = dataColumn + rowIndex;
//                if (dataDic.containsKey(dicKey)) {
//                    String value = dataDic.get(dicKey);
//                    //发现开始标志,打开写入开关
//                    if (startContent.equals(value))
//                        isSave = true;
//                    //发现停止标志,打开中断开关
//                    if (endContent.equals(value))
//                        breakFlag = true;
//                    //如果写入开关打开,写入到Map中
//                    if (isSave) {
//                        map.put(newColumn, value);
//                        saveNum++;
//                    }
//                } else {
//                    if (isSave)
//                        map.put(newColumn, "");
//                }
//            }
//            //跳出信号收到,中断写入
//            if (breakFlag)
//                break;
//            //打开写入开关,且不是开始标记,把Map存储到List中
//            if (isSave && saveNum > 1)
//                result.add(map);
//        }
//        return result;
//    }
//}
