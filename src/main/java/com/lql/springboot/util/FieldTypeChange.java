package com.lql.springboot.util;

import java.util.*;

/**
 * Created by lql on 2018/8/31 13:45
 * <p>
 * 将数据库字段类型转换为前端识别的数据类型
 **/
public class FieldTypeChange {

    /**
     * 可根据后续业务拓展添加需转换的字段类型
     */
    public static final String[] STRING_ARRAY = {"VARCHAR2", "NVARCHAR2", "CHAR"};
    public static final String[] INTEGER_ARRAY = {"NUMBER", "INT", "INTEGER"};
    public static final String[] DATE_ARRAY = {"DATE", "TIMESTAMP"};

    /**
     * @param list
     * @return
     */
    public static List<Map<String, Object>> changeFieldType(List list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.size() > 0) {
                Map<String, Object> map = (Map<String, Object>) list.get(i);
                String key = String.valueOf(map.get("TYPE")) == "null" ? "" : String.valueOf(map.get("TYPE"));
                if (!key.equals("")) {
                    String fieldType = key.substring(0, key.indexOf("(") > 0 ? key.indexOf("(") : key.length()).toUpperCase();
                    boolean isString = Arrays.asList(STRING_ARRAY).contains(fieldType);
                    boolean isInteger = Arrays.asList(INTEGER_ARRAY).contains(fieldType);
                    boolean isDate = Arrays.asList(DATE_ARRAY).contains(fieldType);
                    if (isString) {
                        map.replace("TYPE", "string");
                    } else if (isInteger) {
                        map.replace("TYPE", "integer");
                    } else if (isDate) {
                        map.replace("TYPE", "date");
                    } else {
                        throw new UnsupportedOperationException("暂不支持的数据类型转换!!!");
                    }
                }
            }
        }
        return list;
    }

    /**
     * 替换list-map中的key，将数据库下划线字段转换为驼峰
     * @param list
     * @return
     */
    public static Object changLineKeyToHump(List<Map<String, Object>> list) {
        List list1 = new ArrayList();
        if (list.size() > 0) {
            for (Map map : list) {
                HashMap map1 = new HashMap();
                for (Object key : map.keySet()) {
                    map1.put(FieldTypeChange.replaceWithHump((String) key), map.get(key));
                }
                list1.add(map1);
            }
        }
        return list1;
    }

    /**
     * 将驼峰字符串转化为下划线
     * eg:commonKey--->COMMON_KEY
     *
     * @param s
     * @return
     */
    public static String replaceWithLine(String s) {

        String[] strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (String string : strings) {
            s = s.replace(string, "_" + string);
        }
        return s.toUpperCase();
    }

    /**
     * 将下划线字符串转化为驼峰
     * eg:COMMON_KEY--->commonKey
     *
     * @param s
     * @return
     */
    public static String replaceWithHump(String s) {
        s = s.toLowerCase();
        StringBuilder result = new StringBuilder();
        if (s != null && s.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }


}
