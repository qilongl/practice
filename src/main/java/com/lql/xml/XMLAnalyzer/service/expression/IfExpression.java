package com.lql.xml.XMLAnalyzer.service.expression;


import com.googlecode.aviator.AviatorEvaluator;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.util.ExpressionUtil;
import com.lql.xml.XMLAnalyzer.util.HtmlParserUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;


/**
 * Created by lql on 2017/8/15.
 */
public class IfExpression implements Serializable{
    private static Logger logger = LoggerFactory.getLogger(ExpressionUtil.class);

    /***
     * if条件表达式执行,返回执行后的sql
     * @param sql
     * @param jsonObject
     * @return
     */
    public static String exec(String sql, BusiConfig busiConfig, JSONObject inAllParams, Map<String,Object> jsonObject)
    {
        /***
         * 找到单个表达式的完整sql语句区域
         */
        int startIndex=sql.indexOf(ExpressionUtil.If);
        int endIndex=ExpressionUtil.getCloseExpressionIndex(sql,ExpressionUtil.If,ExpressionUtil.endIf);
        StringBuffer sqlBuffer=new StringBuffer(sql);
        String ifContent=sqlBuffer.substring(startIndex,endIndex);
        //取出表达式原文
        String expression= HtmlParserUtil.getValue(sql,"test=\"","\">");
        boolean flag=ExpressionUtil.calExpression(expression,busiConfig,inAllParams,jsonObject);
        if(flag)
        {
            String trueSql=ifContent.substring(ifContent.indexOf(">")+1,ifContent.lastIndexOf("</if>"));
            sqlBuffer=sqlBuffer.replace(startIndex,endIndex,trueSql);
        }
        else
        {
            sqlBuffer=sqlBuffer.replace(startIndex,endIndex,"");
        }
        return sqlBuffer.toString();
    }

    public static void main(String []args)
    {
        boolean flag = (boolean) AviatorEvaluator.execute("[\n" +
                "    {\n" +
                "        \"userId\": \"CD02A4940A7C4C3284CA323138B5F1C0\",\n" +
                "        \"instanceId\": \"a196da2c1f29460ebf67a81deac21001\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"userId\": \"FB0B58EDB5FD4C3F874BDD10E95DDCC3\",\n" +
                "        \"instanceId\": \"1012fedfc17a46118462dd5671bdb8e8\"\n" +
                "    }\n" +
                "]");
        boolean ff = (boolean) AviatorEvaluator.execute("'张扬'=='张扬'");
        System.out.println(flag);
        System.out.println(ff);
    }
}
