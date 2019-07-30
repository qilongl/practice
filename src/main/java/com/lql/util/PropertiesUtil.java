package com.lql.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;

/**
 * Created by json on 2018/4/19.
 */
public class PropertiesUtil {
    /***
     * 获取资源文件
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Configuration getDirConfig(String fileName) throws Exception {
        Configuration configuration = null;
        //1.先从jar文件所在目录获取
        String path = PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (path.indexOf(".jar") != -1) {
            path = path.substring(0, path.indexOf(".jar"));
            path = path.substring(0, path.lastIndexOf(File.separator) + 1);
            File file = new File(path + fileName);
            if (file.exists()) {
                configuration = new PropertiesConfiguration(file);
            } else {
                configuration = new PropertiesConfiguration(fileName);
            }
            //2.如果获取失败，才从jar包内部获取
        } else {
            configuration = new PropertiesConfiguration(fileName);
        }
        return configuration;
    }

    public static void main(String[] args) throws Exception {
        String path2 = PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println(path2);
        Configuration q1 = getDirConfig("db.properties");
        System.out.println();
    }
}
