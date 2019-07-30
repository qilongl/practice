package com.lql.xml.beans;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lql on 2017/5/17.
 */
public class UserDataService {
    // 取出用户信息到字典中
    public static JSONObject getSystemParameter(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(SystemParameters.USERID,"111111111");
        jsonObject.put(SystemParameters.USERNAME,"jack");
        jsonObject.put(SystemParameters.DEPARTMENTID,"22222222");
        jsonObject.put(SystemParameters.DEPARTMENTNAME,"投资研究部");
        return jsonObject;
    }
}
