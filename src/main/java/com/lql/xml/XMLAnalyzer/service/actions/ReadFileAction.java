package com.lql.xml.XMLAnalyzer.service.actions;

import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.*;

/**
 * Created by json on 2018/5/11.
 * 读取文件正文
 */
public class ReadFileAction implements Serializable {
    public static final String STRING = "STRING";
    public static final String CONTENT = "CONTENT";
    private BusiConfig busiConfig;

    public ReadFileAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    public List<Map<String, Object>> readFileCommand(Action cmd, JSONObject inParms) throws Exception {
        //定义结果集
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            String abspath = cmd.getAbspath();
            //读取的文件路径
            String filePath = busiConfig.getValue(cmd, abspath);
            File file = new File(filePath);
            if (!file.exists())
                throw new UnknownFormatConversionException(filePath + "文件不能存在！");

            switch (cmd.getContenttype().toUpperCase()) {
                case STRING:
                    Map map = new HashMap<>();
                    map.put(CONTENT, readString(file));
                    result.add(map);
                    break;
                default:
                    throw new UnsupportedOperationException("框架暂不支持" + cmd.getContenttype() + "类型的正文读取");
            }
        } catch (Exception ex) {
            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
        }
        return result;
    }

    /***
     * 读取文件的正文
     *
     * @param file
     * @return
     * @throws Exception
     */
    private String readString(File file) throws Exception {
        String encoding = "UTF-8";
        Long filelength = file.length();
        FileInputStream in = null;
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            in = new FileInputStream(file);
            in.read(filecontent);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != in)
                in.close();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("The OS does not support " + encoding);
        }
    }

    public static void main(String args[]) throws Exception {
        ReadFileAction readFileAction = new ReadFileAction(null);
        System.out.println(readFileAction.readString(new File("c://unintall.log")));
    }
}
