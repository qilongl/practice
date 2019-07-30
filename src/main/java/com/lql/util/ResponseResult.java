package com.lql.util;

import java.io.Serializable;

/**
 * Created by lql on 2017/5/25.
 */
public class ResponseResult implements Serializable {
    public static final String RESULT_STATUS_CODE_SUCCESS = "200";
    public static final String RESULT_STATUS_CODE_ERROR = "500";
    public static final String RESULT_STATUS_CODE_DEFAULT = "9";
    //登录状态返还码
    public static final String LOGIN_STATUS_CODE_ACCOUNT_NO = "201";//账号不存在
    public static final String LOGIN_STATUS_CODE_WRONG_PASSWORD = "202";//密码错误
    public static final String LOGIN_STATUS_CODE_MANY_FAILURES = "203";//登录失败次数过多
    public static final String LOGIN_STATUS_CODE_CODE_ERROR = "204";//验证码错误
    public static final String LOGIN_STATUS_CODE_CODE_INVALID = "205";//验证码失效
    public static final String LOGIN_STATUS_CODE_ACCOUNT_NO_OR_WRONG_PASSWORD = "206";//验证码失效

    /**
     * 执行成功标识
     */
    private String statusCode = RESULT_STATUS_CODE_DEFAULT;

    /**
     * 执行提示信息
     */
    private String msg = "";

    /**
     * 执行返回结果对象
     */
    private Object result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "statusCode=" + statusCode +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
