package com.lql.xml.XMLAnalyzer.converter;

import com.lql.util.StringHelper;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.service.impl.BusiConfigCache;
import com.lql.xml.XMLAnalyzer.service.impl.BusiService;
import com.lql.xml.db.DPService;
import com.lql.xml.inf.IConverter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by json on 2018/3/26.
 * 重新加载指定路径的业务文件到内存
 */
public class ReloadXmlAction implements IConverter {

    @Override
    public Object exec(Action cmd, JSONObject jsonObject, Map<String, List<Map<String, byte[]>>> fileMap, Object dataSet, DPService dpService) throws Exception {
        BusiService busiService = (BusiService) BusiConfigCache.ctx.getBean("busiService");
        List<Map> list = (List) dataSet;
        String reqUrl = StringHelper.toString(list.get(0).get("reqUrl"));
        busiService.reloadBusiConfig(reqUrl, dpService);
        return null;
    }
}
