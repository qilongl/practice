package com.lql.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Created by lql on 2018/10/12 11:14
 **/
public class RestUrlUtil {

    /**
     * 获取请求服务的url
     *
     * @param ip
     * @param port
     * @param serverName
     * @return
     */
    public static String getRestUrl(String ip, String port, String serverName) {
        return "http://" + ip + ":" + port + "/" + serverName;
    }

    public static String getRestUrl(String port, String serverName) {
        return "http://localhost" + ":" + port + "/" + serverName;
    }
}
