package com.lql.xml.XMLAnalyzer.service.expression;


import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.util.ExpressionUtil;
import com.lql.xml.XMLAnalyzer.util.HtmlParserUtil;
import com.lql.xml.XMLAnalyzer.util.SystemFunction;
import com.lql.xml.XMLAnalyzer.util.TypeUtil;
import com.lql.util.StringHelper;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/8/15.
 */
public class ForeachExpression implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ForeachExpression.class);

    /***
     * <foreach collection="#{content}" item="tt" separator=";">
     * and reportcontent=#{tt} or title=#{tt}
     * </foreach>
     *
     * @param sql
     * @param jsonObject
     * @return
     */
    public static String exec(String sql, BusiConfig busiConfig, JSONObject inAllParams, Map<String, Object> jsonObject, Map paramMap) throws Exception {
        /***
         * 找到单个表达式的完整sql语句区域
         */
        int startIndex = sql.indexOf(ExpressionUtil.Foreach);
        int endIndex = ExpressionUtil.getCloseExpressionIndex(sql, ExpressionUtil.Foreach, ExpressionUtil.endForeach);
        StringBuffer sqlBuffer = new StringBuffer(sql);
        String foreachContent = sqlBuffer.substring(startIndex, endIndex);
        String foreachSqlContent = foreachContent.substring(foreachContent.indexOf(">") + 1, foreachContent.lastIndexOf("</foreach>"));
        String collection = HtmlParserUtil.getValue(foreachContent, "collection=\"#{", "}");
        String item = HtmlParserUtil.getValue(foreachContent, "item=\"", "\"");
        String separator = HtmlParserUtil.getValue(foreachContent, "separator=\"", "\"");
        String target = "";
        if (collection.indexOf(".") != -1) {
            String paramName = collection.substring(0, collection.indexOf("."));
            String attr = collection.substring(collection.indexOf(".") + 1, collection.length());
            List<Map<String, Object>> list = TypeUtil.changeToListMap(busiConfig.getParamsFromInParams_CmdResult(paramName, inAllParams));
            if (list.size() != 1)
                throw new UnsupportedOperationException(foreachContent + "collection 指定了外部参数" + paramName + "但参数大小不为1,实际是" + list.size());
            Map<String, Object> objectMap = list.get(0);
            target = objectMap.get(attr).toString();
        } else {
            target = StringHelper.toString(jsonObject.get(collection));
        }
        StringBuffer newSql = new StringBuffer();
        String[] targetList = target.split(separator);
        for (int i = 0; i < targetList.length; i++) {
            String guid = SystemFunction.get("guid");
            newSql.append(foreachSqlContent.replaceAll("#\\{" + item + "\\}", "@{" + guid + "}"));
            paramMap.put(guid, targetList[i]);
        }
        sqlBuffer = sqlBuffer.replace(startIndex, endIndex, newSql.toString());
        return sqlBuffer.toString();
    }

    public static void main(String[] args) throws Exception {
//        String sql = "<foreach collection=\"#{content}\" item=\"tt\" separator=\";\">\n" +
//                "            and reportcontent=#{tt} or title=#{tt}\n" +
//                "<if test=\"test==1\"> " +
//                "and username like '张__'" +
//                "</if>" +
//                "        </foreach>";
//        Document document = DocumentHelper.parseText(sql);
//        Element rootElement = document.getRootElement();
//        List<Element> list = rootElement.elements();
//        System.out.println(rootElement.getName());
//        for (int i = 0; i < list.size(); i++) {
//            Element element = list.get(i);
//            System.out.println(element.getName());
//        }

        Map map=new HashMap<>();
        map.put("1","你好");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("2","哈喽");
        Map<String,Object> mm=jsonObject;
        System.out.println(mm.get("2"));
    }
}
