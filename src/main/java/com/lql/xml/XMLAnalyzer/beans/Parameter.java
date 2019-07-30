package com.lql.xml.XMLAnalyzer.beans;

import java.io.Serializable;

/**
 * Created by lql on 2017/5/25.
 * 入参中属性+默认值 模型
 */
public class Parameter implements Serializable {
    /***
     * 入参中其中一个参数的名称
     */
    private String name;
    /***
     * 入参中其中一个参数的默认值
     */
    private String defaultValue;
    /****
     * 是否必填
     */
    private boolean required;
    /****
     * 正则表达式
     */
    private String expression;
    /****
     * 当正则表达式匹配是失败时的描述
     */
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getDefaultValue() {

        return defaultValue;
    }


    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
