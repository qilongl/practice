package com.lql.springboot.beans;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @Author lql
 * @Date 2018/7/4 14:29
 */
public class SysConfigrationBean {
    public static final String sysConfig = "sys.properties";
    //    public static final String sysbasedir = "sysbasedir";
//    public static final String userbasedir = "userbasedir";
    public static String attachmentKey = "attachmentPath";

    public static String getAttachmentPath() throws ConfigurationException {
        return getConfigValue(attachmentKey);
    }


    //从配置文件根据k获取value
    public static String getConfigValue(String key) throws ConfigurationException {
        Configuration configuration = new PropertiesConfiguration(sysConfig);
        return configuration.getString(key);
    }
}
