package com.lql.util;

import org.apache.log4j.Logger;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lql on 2017/6/29.
 * 文件处理的工具类
 */
public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class);

    /***
     * 获取相对目录下特定后缀名的文件集合
     *
     * @param dirs
     * @param stuffix
     * @return
     */
    public static List<File> getFiles(String dirs, String stuffix) {
        logger.debug("\t读取" + dirs + "目录下业务文件");
        List<File> allFiles = new ArrayList<>();
        String basePath = ClassUtils.getDefaultClassLoader().getResource(dirs).getPath();
        File dir = new File(basePath);
        getAllFile(dir, stuffix, allFiles);
        return allFiles;
    }

    /****
     * 读取该dir目录下面的所有的stuffix 结尾的文件到allFiles 中
     *
     * @param dir
     * @param stuffix
     * @param allFiles
     */
    public static void getAllFile(File dir, String stuffix, List<File> allFiles) {
        File[] files = dir.listFiles();
        for (File a : files) {
            if (a.isFile() && a.getName().toLowerCase().endsWith(stuffix)) {
                allFiles.add(a);
            } else if (a.isDirectory()) {
                getAllFile(a, stuffix, allFiles);
            }
        }
    }


    /****
     * 获取到当前业务文件的请求路径
     * @param absPath
     * @param sysbase
     * @param userbase
     * @return
     */
    public static String getNameKey(String absPath,String sysbase,String userbase)
    {
        String moduleUrl="";
        absPath=absPath.replaceAll("/",File.separator);
        if(absPath.contains(sysbase))
        {
            moduleUrl=absPath.substring(absPath.indexOf(sysbase),absPath.length());
            moduleUrl=moduleUrl.replace(File.separator,"-");
            moduleUrl=moduleUrl.substring(0,moduleUrl.lastIndexOf("-"));
        }
        else
        {
            moduleUrl=absPath.substring(absPath.indexOf(userbase),absPath.length());
            moduleUrl=moduleUrl.replace(File.separator,"-");
            moduleUrl=moduleUrl.substring(0,moduleUrl.lastIndexOf("-"));
        }
        return moduleUrl;
    }
    public static void main(String args[])
    {
        String path="C:\\workspace\\SVN\\cbd-cloud\\service-expand\\src\\main\\resources\\busiFiles\\Electronic\\addReport.xml";
//        List<File> files=FileUtil.getFiles(path,".xml");
//        System.out.println(files.size());
//        String nameKey=FileUtil.getNameKey("busiFiles",new File(path));
//        System.out.println(nameKey);
        List<File> allFiles=new ArrayList<>();
        getAllFile(new File("C:\\Users\\lql\\Desktop\\girl\\files"),".xml",allFiles);
        System.out.println(allFiles.toString());
    }
}
