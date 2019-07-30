package com.lql.util;

import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * @Author lql
 * @Date 2018/6/13 18:28
 */
public class JsonValueFilter {
    //空值过滤为""
    public static ValueFilter changeNullToString() {
        ValueFilter filter = new ValueFilter() {
            @Override
            public Object process(Object obj, String s, Object v) {
                if (v == null)
                    return "";
                return v;
            }
        };
        return filter;
    }
}
