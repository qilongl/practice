package com.lql.xml.XMLAnalyzer.service.actions;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.XMLAnalyzer.service.impl.BusiConfigCache;
import com.lql.xml.XMLAnalyzer.util.TypeUtil;
import com.lql.xml.db.DPService;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/5/25.
 */
public class ImportAction implements Serializable {
    private BusiConfig busiConfig;

    public ImportAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    /***
     * 引入其他的业务配置文件
     *
     * @param cmd
     * @param inParam
     * @return
     * @throws Exception
     */
    public void importCommand(Action cmd, JSONObject inParam, Map<String, List<Map<String, byte[]>>> fileMap, Map<String, Object> result, DPService dpService) throws Exception {
        try {
            String source = "";
            String content = cmd.getContent();
            Document document = DocumentHelper.parseText(content);
            Element rootElement = document.getRootElement();
            source = rootElement.attributeValue("source");//引用的目标
            /***
             * 取出busiconfig的配置副本
             */
            if (!BusiConfigCache.configDic.containsKey(source))
                throw new UnsupportedOperationException("内存中不存在要应用的" + source + ".xml业务文件!");
            BusiConfig sourceBusiConfig = BusiConfigCache.configDic.get(source).myClone();
            JSONObject jsonObject = new JSONObject();// 构建输入参数
            List<Element> elementList = rootElement.elements();
            for (int i = 0; i < elementList.size(); i++) {
                Element element = elementList.get(i);
                String targetParamName = element.getName();// 引用对象的入参名称
                String inParamName = element.attributeValue("params");// 传入的参数名称
                // 取到要传入的参数集合,构建入参
                List<Map<String, Object>> params = TypeUtil.changeToListMap(busiConfig.getParamsFromInParams_CmdResult(inParamName, inParam));
                com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseArray(JSON.toJSONString(params));
                jsonObject.put(targetParamName, jsonArray);
            }
            String sourceBusiconfigDataSourceName = sourceBusiConfig.getDataSource();
            if (!sourceBusiconfigDataSourceName.equals(((DruidDataSource) dpService.getDataSource()).getName())) {
                throw new UnsupportedOperationException("import标签不支持引入不同数据源的业务文件!");
            }
            /***
             * 设置数据操作类
             */
            sourceBusiConfig.setDpService(dpService);
            Map<String, Object> resultMap = sourceBusiConfig.exec(jsonObject, fileMap);// 调用
            String repeatedKey = hasRepeatedKey(busiConfig.getCmdList(), resultMap);
            if (repeatedKey != null)
                throw new UnsupportedOperationException("被import的业务文件" + cmd.getSource() + ".xml中包含有与主" + "-" + busiConfig.getId() + "xml中重复的id定义:" + repeatedKey);
            result.putAll(resultMap);
        } catch (UserException ex) {
            throw new UserException(ex.getId(), ex.getErrorId(), "", ex);
        }
    }

    /***
     * 检查两个result 中是否包含重复的key
     *
     * @param cmdList
     * @param map2
     * @return
     */
    private String hasRepeatedKey(List<Action> cmdList, Map map2) {
        for (int i = 0; i < cmdList.size(); i++) {
            Action cmd = cmdList.get(i);
            if (map2.containsKey(cmd.getId()))
                return cmd.getId();
        }
        return null;
    }
}
