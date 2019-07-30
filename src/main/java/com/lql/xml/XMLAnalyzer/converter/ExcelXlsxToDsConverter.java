//package com.lql.xml.XMLAnalyzer.converter;
//
//import com.cbdcloud.util.ReadExcelHelper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//
///**
// * Created by deng on 2017/9/20.
// */
//public class ExcelXlsxToDsConverter {
//    //入参中路径的key
//    public static final String PATH = "PATH";
//    public static final String VALUE = "value";
//    private static Logger logger = LoggerFactory.getLogger(ExcelXlsxToDsConverter.class);
//
//    public List<Map<String, Object>> read(List<Map<String, Object>> filePath, List<Map<String, Object>> sheetName, List<Map<String, Object>> columnConfig, List<Map<String, Object>> startIndex) throws Exception {
//        /***
//         * 取出参数
//         */
//        Map<String, Object> param = filePath.get(0);
//        String path = param.get(PATH).toString();
//        param = startIndex.get(0);
//        int startRow = Integer.parseInt(param.get(VALUE).toString());
//        param = sheetName.get(0);
//        String sheetname = param.get(VALUE).toString();
//        param = columnConfig.get(0);
//        /***
//         * 构建新的列头columnMap,方便读取
//         */
//        Map columnMap = new HashMap<>();
//        List<String> columnNameList=new ArrayList<>();
//        Iterator it = param.keySet().iterator();
//        while (it.hasNext()) {
//            String key = it.next().toString();
//            String value = param.get(key).toString();
//            if (columnMap.containsKey(value))
//                throw new UnsupportedOperationException("excel 列配置参数错误,出现重复的列:" + value);
//            columnMap.put(value, key);
//            columnNameList.add(value);
//        }
//        /***
//         * 读取excel文件
//         */
//        List<Map<String, Object>> result = new ArrayList<>();
//        ReadExcelHelper helper = new ReadExcelHelper();
//        Map<String, String> mapResult = helper.read(path, sheetname);
//        Calendar start=Calendar.getInstance();
//        while (true) {
//            Map<String, Object> rsMap = getOneRowData(startRow, columnMap, mapResult);
//            if (null == rsMap)
//                break;
//            result.add(rsMap);
//            startRow++;
//        }
//        Calendar end=Calendar.getInstance();
//        logger.debug("构建数据集总数:"+result.size()+",耗时:"+(end.getTimeInMillis()-start.getTimeInMillis())/(1000)+"s");
//        return result;
//    }
//
//    /***
//     * 递归构建所有行
//     *
//     * @param rowIndex
//     * @param colsMap
//     * @param dic
//     */
//    private Map<String, Object> getOneRowData(int rowIndex, Map<String, String> colsMap, Map<String, String> dic) {
//        Map<String, Object> map = new HashMap<>();
//        int nullNum = 0;
//        Iterator iterator = colsMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            String dataColumn = iterator.next().toString();
//            String newDataColumn = colsMap.get(dataColumn);
//            String dataKey = dataColumn + rowIndex;
//            if (dic.containsKey(dataKey)) {
//                String value = dic.get(dataKey);
//                map.put(newDataColumn, value);
//            } else {
//                nullNum++;
//                map.put(newDataColumn, "");
//            }
//        }
//        //当前行的数据不是全部为空
//        if (colsMap.size() > nullNum) {
//            return map;
//        }//当前行没有取到一条有效数据
//        else {
//            return null;
//        }
//    }
//}
