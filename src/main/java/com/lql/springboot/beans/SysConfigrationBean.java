package com.lql.springboot.beans;

import com.lql.util.StringHelper;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.UnsupportedEncodingException;

/**
 * @Author lql
 * @Date 2018/7/4 14:29
 */
public class SysConfigrationBean {
    public static final String sysConfig = "sys.properties";
    public static String attachmentKey = "attachmentPath";

    public static String getAttachmentPath() {
        return getConfigValue(attachmentKey);
    }


    /**
     * 从配置文件根据k获取value
     */
    public static String getConfigValue(String key) {
        return getConfigValue(sysConfig, key);
    }

    public static String getConfigValue(String configName, String key) {
        String result = "";
        try {
            Configuration configuration = new PropertiesConfiguration(configName);
            String value = configuration.getString(key);
            result = StringHelper.stringEncoding2UTF8(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(getConfigValue("dizhi"));
    }
}
