package com.lql.xml.inf;

import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.db.DPService;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/9/19.
 * 可根据具体业务需求，对结果集进行处理，实现该接口
 */
public interface IConverter {
    /****
     * 转换器接口
     *
     * @param jsonObject 请求中入参json
     * @param fileMap    请求中的附件字节流
     * @param dataSet    入参数据集
     * @return
     */
    Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) throws Exception;
}
