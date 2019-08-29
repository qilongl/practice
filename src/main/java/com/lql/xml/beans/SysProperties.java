package com.lql.xml.beans;

import com.lql.util.PropertiesUtil;
import com.lql.util.StringHelper;
import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lql on 2017/8/3.
 */
public class SysProperties {

    public static final String DEFAULT_DATASOURCE = "dataSource";// 系统资源默认使用的数据源

    private static Logger logger = LoggerFactory.getLogger(SysProperties.class);
    /***
     * 字典存储所有的key value
     */
    private static Map<String, String> dic = new HashMap<>();

    private static String configFile = "sys.properties";


    private static String fileDir;
    /***
     * 系统业务文件存放的根目录
     */
    private static String sysbasedir;// 最近父级目录
    private static String sysBaseDir;// 绝对路径
    /****
     * 用户业务文件存放的根目录
     */
    private static String userbasedir;// 最近父级目录
    private static String userBaseDir;// 绝对路径

    private static String baseDic;//所有配置文件的根目录

    private static String isPrintErrorDetail;//是否输入异常详情

    private static String isProduction;//是否生产环境

    /***
     * 附件存储路径
     */
    private static String attachmentPath;

    public static String getIsProduction() {
        return isProduction;
    }

    public static void setIsProduction(String isProduction) {
        SysProperties.isProduction = isProduction;
    }

    /***
     * token有效期
     */
    private static String tokenValidity;

    public static String getFileDir() {
        return fileDir;
    }

    public static String getSysbasedir() {
        return sysbasedir;
    }

    public static String getUserbasedir() {
        return userbasedir;
    }

    public static String getTokenValidity() {
        return tokenValidity;
    }

    public static String getSysBaseDir() {
        return sysBaseDir;
    }

    public static String getUserBaseDir() {
        return userBaseDir;
    }

    public static String getAttachmentPath() {
        return attachmentPath;
    }

    public static String getIsPrintErrorDetail() {
        return isPrintErrorDetail;
    }

    public static void setIsPrintErrorDetail(String isPrintErrorDetail) {
        SysProperties.isPrintErrorDetail = isPrintErrorDetail;
    }

    /***
     * 初始化系统配置
     *
     * @throws Exception
     */
    public static void init() throws Exception {
        /**由于这种方式打成JAR时会出现异常信息，暂时不用以下的代码 beign**/
//        String basePath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//        Ficlient-cloud-0.0.1-SNAPSHOT.jarle file = new File(basePath + File.separator + configFile);
//        Properties sysConfig = new Properties();
//        sysConfig.load(new FileReader(file));
//        String sysbasedir = sysConfig.getProperty("sysbasedir");
//        String userbasedir = sysConfig.getProperty("userbasedir");
        /**由于这种方式打成JAR时会出现异常信息，暂时不用以下的代码 end**/
        /**new get properties begin**/
        Configuration configuration = PropertiesUtil.getDirConfig(configFile);
        fileDir = StringHelper.stringEncoding2UTF8(configuration.getString("fileDir"));
        String sysbasedir = StringHelper.stringEncoding2UTF8(configuration.getString("sysbasedir"));
        String userbasedir = StringHelper.stringEncoding2UTF8(configuration.getString("userbasedir"));
        /**new get properties end**/
        sysBaseDir = sysbasedir;
        userBaseDir = userbasedir;
        tokenValidity = StringHelper.stringEncoding2UTF8(configuration.getString("tokenValidity"));
        attachmentPath = StringHelper.stringEncoding2UTF8(configuration.getString("attachmentPath"));
        isPrintErrorDetail = StringHelper.stringEncoding2UTF8(configuration.getString("isPrintErrorDetail"));
        isProduction = StringHelper.stringEncoding2UTF8(configuration.getString("isProduction"));
        SysProperties.sysbasedir = sysbasedir.substring(sysbasedir.lastIndexOf(File.separator) + 1, sysbasedir.length());
        SysProperties.userbasedir = userbasedir.substring(userbasedir.lastIndexOf(File.separator) + 1, userbasedir.length());
        Iterator it = configuration.getKeys();
        dic.clear();
        while (it.hasNext()) {
            String key = it.next().toString();
            String value = StringHelper.stringEncoding2UTF8(configuration.getString(key));
            dic.put(key, value);
        }
        baseDic = sysbasedir.substring(0, sysbasedir.lastIndexOf(File.separator));
        dic.put("baseDir", baseDic);
    }

    /***
     * 通过key获取配置文件中的值
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        if (dic.containsKey(key))
            return dic.get(key);
        else
            throw new UnsupportedOperationException("您尝试获取未定义的系统属性" + key);
    }
}
