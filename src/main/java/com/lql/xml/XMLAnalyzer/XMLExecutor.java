package com.lql.xml.XMLAnalyzer;

import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.db.DPService;
import com.lql.util.FileUtil;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/12/29.
 */
public class XMLExecutor implements Serializable {
    /***
     * xml根目录:d:/test/
     */
    private String xmlRootPath;
    /****
     * 附件地址
     */
    private String attachMentPath;
    /***
     * 入参
     */
    private String jsonParams;

    public static Map<String, BusiConfig> configDic = new HashMap<>();
    /***
     * 数据操作服务
     */
    private DPService dpService;
    /***
     * 是否自动加载xml库
     */
    private boolean isInitXmlDic;
    /***
     * 业务文件的后缀名
     */
    private String postFix = ".xml";

    public XMLExecutor(String xmlRootPath, boolean isInitXmlDic, DPService dpService) throws Exception {
        File dir = new File(xmlRootPath);
        if (!dir.exists())
            throw new UnsupportedOperationException(dir.getAbsolutePath() + "业务文件根目录不存在!");
        this.xmlRootPath = xmlRootPath;
        this.dpService = dpService;
        this.isInitXmlDic = isInitXmlDic;
        if (isInitXmlDic)
            initXmlDic();
    }

    /***
     * 初始化根目录下所有的xml文件到dic中
     */
    private void initXmlDic() {
        /***
         * 加载所有配置文件到dictionary中
         */
        List<File> allFiles = new ArrayList<>();
        FileUtil.getAllFile(new File(xmlRootPath), postFix, allFiles);
    }

}
