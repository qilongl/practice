package com.lql.xml.XMLAnalyzer.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lql on 2017/9/19.
 */
public class ReadExcelUtil {
    //入参中路径的key
    public static final String PATH = "PATH";
    public static final String VALUE = "value";
    private static Logger logger = LoggerFactory.getLogger(ReadExcelUtil.class);

    public List<Map<String, Object>> read(List<Map<String, Object>> filePath, List<Map<String, Object>> sheetName, List<Map<String, Object>> columnConfig, List<Map<String, Object>> startIndex) throws Exception {
        /***
         * 取出参数
         */
        Map<String, Object> param = filePath.get(0);
        String path = param.get(PATH).toString();
        param = startIndex.get(0);
        int startRow = Integer.parseInt(param.get(VALUE).toString());
        param = sheetName.get(0);
        String sheetname = param.get(VALUE).toString();
        param = columnConfig.get(0);
        /***
         * 构建新的列头columnMap,方便读取
         */
        Map columnMap = new HashMap<>();
        Iterator it = param.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            String value = param.get(key).toString();
            if (columnMap.containsKey(value))
                throw new UnsupportedOperationException("excel 列配置参数错误,出现重复的列:" + value);
            columnMap.put(value, key);
        }
        /***
         * 读取excel文件
         */
        List<Map<String, Object>> result = new ArrayList<>();
        Workbook wb = WorkbookFactory.create(new FileInputStream(new File(path)));
        Iterator iterator = wb.iterator();
        while (iterator.hasNext()) {
            Sheet sheet = (Sheet) iterator.next();
            String sName = sheet.getSheetName();
            if (!sName.equals(sheetname)) continue;
            //读取当前sheet
            int lastRow = sheet.getLastRowNum();
            logger.info("开始读取" + sName + "页,总行数:" + lastRow + ",开始读取行:" + startRow);
            for (int i = startRow - 1; i <=lastRow; i++) {
                if(i%1000==0)
                    System.out.println("读取"+i+"行..");
                Map<String, Object> resultMap = new HashMap();
                Row row = sheet.getRow(i);
                int startColumn = row.getFirstCellNum();
                int lastColumn = row.getLastCellNum();
                for (int j = startColumn; j < lastColumn; j++) {
                    Cell cell = row.getCell(j);
                    String titleCode = CellReference.convertNumToColString(cell.getColumnIndex());
                    if (!columnMap.containsKey(titleCode)) continue;
                    String strValue = "";
                    switch (cell.getCellTypeEnum()) {
                        case NUMERIC:
                            double value = cell.getNumericCellValue();
                            short format = cell.getCellStyle().getDataFormat();
                            SimpleDateFormat sdf = null;
                            //日期
                            if (format == 14 || format == 31 || format == 57 || format == 58) {
                                sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = DateUtil.getJavaDate(value);
                                strValue = sdf.format(date);
                            } else if (format == 20 || format == 32) {
                                //时间
                                sdf = new SimpleDateFormat("HH:mm");
                                Date date = DateUtil.getJavaDate(value);
                                strValue = sdf.format(date);
                            } else if (format == 22) {
                                //年月日,时分秒
                                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = DateUtil.getJavaDate(value);
                                strValue = sdf.format(date);
                            } else {
                                //数字
                                strValue = String.valueOf(value);
                            }
                            strValue=strValue.trim();
                            break;
                        case STRING:
                            strValue=cell.getStringCellValue().trim();
                            break;
                        case FORMULA:
                            strValue=cell.getCellFormula().trim();
                            break;
                        case BOOLEAN:
                            strValue=String.valueOf(cell.getBooleanCellValue()).trim();
                            break;
                        case BLANK:
                            strValue = null;
                            logger.warn(i + "行," + titleCode + "列的单元格格式:" + cell.getCellTypeEnum());
                            break;
                        default:
                            logger.error("不支持" + i + "行," + titleCode + "列的单元格格式:" + cell.getCellTypeEnum());
                            throw new UnsupportedOperationException("不支持" + i + "行," + titleCode + "列的单元格格式:" + cell.getCellTypeEnum());
                    }
                    resultMap.put(columnMap.get(titleCode).toString(), strValue);
                }
                result.add(resultMap);
            }
            logger.info(sName + "页,读取完成,读取总行数:" + result.size());
        }
        return result;
    }

    public static void main(String[] args)
    {

    }
}
