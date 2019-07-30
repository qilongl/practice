package com.lql.xml.XMLAnalyzer.beans;

import org.dom4j.Element;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/5/25.
 * 入参
 */
public class Parameters implements Serializable {
    /**
     * 参数名称
     */

    private String name;
    /***
     * 是否批量参数
     */
    private boolean islist = false;
    /***
     * 参数列表
     */
    private Map<String, Parameter> parameterMap = new HashMap<>();

    /***
     * 初始化
     *
     * @param element
     */
    public Parameters(Element element) {
        init(element);
    }

    public Parameters() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean islist() {
        return islist;
    }

    public void setIslist(boolean islist) {
        this.islist = islist;
    }

    public Map<String, Parameter> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Parameter> parameterMap) {
        this.parameterMap = parameterMap;
    }

    /***
     * 初始化一个参数对象
     *
     * @param element
     */
    public void init(Element element) {
        if (null == element) return;
        String name = element.getName();
        String islist = element.attributeValue("islist");
        List<Element> elementList = element.elements();
        for (int j = 0; j < elementList.size(); j++) {
            Element element2 = elementList.get(j);
            String p_name = element2.getName();
            String defaultValue = element2.attributeValue("default");
            String required=element2.attributeValue("required");
            String expression=element2.attributeValue("expression");
            String desc=element2.attributeValue("desc");
            expression=null==expression?"":expression;
            expression="null".equalsIgnoreCase(expression)?"":expression;
            desc=null==desc?"":desc;
            desc="null".equalsIgnoreCase(desc)?"":desc;
            Parameter parameter = new Parameter();
            parameter.setName(p_name);
            parameter.setDefaultValue(defaultValue);
            parameter.setRequired(null != required && required.equals("true") ? true : false);
            parameter.setExpression(expression);
            parameter.setDesc(desc);
            parameterMap.put(parameter.getName(), parameter);
        }
        setName(name);
        setIslist(null != islist && islist.equals("true") ? true : false);
        setParameterMap(parameterMap);
    }
}
