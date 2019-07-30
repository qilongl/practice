package com.lql.xml.XMLAnalyzer.service.actions;

import com.lql.util.StringHelper;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.XMLAnalyzer.util.TypeUtil;
import com.lql.xml.XMLAnalyzer.util.WriteExcelUtil;
import com.lql.xml.beans.SysProperties;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by lql on 2017/9/11.
 * 创建文件的命令
 */
public class CreateFileAction implements Serializable {

    public static final String xls = ".XLS";
    public static final String doc = ".DOC";
    public static final String txt = ".TXT";
    public static final String xlsx = ".XLSX";
    public static final String xml = ".XML";
    public static final String ORINAME = "ORINAME";
    public static final String PATH = "PATH";
    public static final String PLACEHOLDER = "$";
    private BusiConfig busiConfig;

    public CreateFileAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    public static void main(String[] args) throws Exception {
        SysProperties.init();
        String dirPath = SysProperties.getAttachmentPath() + File.separator + "tmp";
        File file = new File(dirPath);
        if (file.exists()){
            System.out.println(file.getAbsolutePath() + File.separator);
            File file2 = new File(file.getAbsolutePath() + File.separator);
            if (!file2.exists()){
                file2.mkdirs();
            }else System.out.println(file2.getAbsolutePath());

        }else
            file.mkdirs();
    }

    /***
     * 创建文件的命令
     *
     * @param cmd
     * @return
     */
    public List<Map<String, Object>> createFileCommand(Action cmd, JSONObject inParms) throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            String fileName = cmd.getFilename();
            String params = cmd.getParams();
            String propertiesName = busiConfig.getValue(cmd, cmd.getPropertyname());
            String fileNameValue = busiConfig.getValue(cmd, fileName);
            String savePath = busiConfig.getValue(cmd, cmd.getSavepath());
            String baseDir = busiConfig.getValue(cmd, cmd.getBasesavepath());
//            StringBuffer fileNameBuffer = new StringBuffer(fileName);
//            int index1 = fileNameBuffer.indexOf("#{");
//            int index2 = fileNameBuffer.indexOf(".");
//            int index3 = fileNameBuffer.indexOf("}");
//            if (index1 < index2 && index2 < index3 && index1 != -1)//#{a.b}
//            {
//                String name = fileNameBuffer.substring(index1 + 2, index2);
//                String attr = fileNameBuffer.substring(index2 + 1, index3);
//                List<Map<String, Object>> list = TypeUtil.changeToListMap(busiConfig.getParamsFromInParams_CmdResult(name, inParms));
//                if (list.size() != 1)
//                    throw new UnsupportedOperationException(name + "数据集大小不等于1,实际值:" + list.size());
//                Map<String, Object> map = list.get(0);
//                fileNameValue = map.get(attr).toString();
//            } else if (index1 == index3 && index3 == -1)// 文本
//            {
//                fileNameValue = fileName;
//            } else    // 非法
//            {
//                throw new UnsupportedOperationException("不支持的属性配置:" + fileName);
//            }
            Object allData = busiConfig.getParamsFromInParams_CmdResult(params, inParms);
//            String baseDir = ClassLoader.getSystemClassLoader().getResource("").getPath();
            String dirPath = SysProperties.getAttachmentPath() + File.separator + "tmp";
            File dir = new File(dirPath);
            if (!dir.exists())
                dir.mkdirs();
            //创建临时压缩文件
            String filepath = "";
            if (StringHelper.isNotNull(baseDir)) {
                filepath = baseDir + File.separator + savePath + File.separator;
                dir = new File(filepath);
                if (!dir.exists())
                    dir.mkdirs();
                filepath = filepath + fileNameValue;
            } else {
                filepath = dir.getAbsolutePath() + File.separator;
                dir = new File(filepath);
                if (!dir.exists())
                    dir.mkdirs();
                filepath = filepath + UUID.randomUUID();
            }
            String postFix = fileNameValue.substring(fileNameValue.lastIndexOf("."), fileNameValue.length());
            switch (postFix.toUpperCase()) {
                case xls:
                    writeExcel(allData, filepath, fileNameValue, "xls");
                    break;
                case xlsx:
                    writeExcel(allData, filepath, fileNameValue, "xlsx");
                    break;
                case doc:
                    writeDoc(allData, filepath);
                    break;
                case txt:
                    writeListMapToTxt(allData, filepath);
                    break;
                case xml:
                    if (StringHelper.isNotNull(propertiesName)) {
                        List<Map<String, Object>> data = TypeUtil.changeToListMap(allData);
                        Map<String, Object> dataObj = data.get(0);
                        if (!dataObj.containsKey(propertiesName))
                            throw new UnsupportedOperationException(params + "的结果集中不含有属性" + propertiesName);
                        Object object = dataObj.get(propertiesName);
                        String content = StringHelper.toString(object);
                        String xmlTitle = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                        if (content.indexOf("<?xml") == -1)
                            content = xmlTitle.concat(content);
                        writeStringContentToFile(content, filepath);
                    } else {
                        throw new UnsupportedOperationException("需要给出明确的propertyname属性!");
                    }
                    break;
                default:
                    throw new UnsupportedOperationException("不支持的创建文件的格式:" + postFix);
            }
            Map<String, Object> mapResult = new HashMap();
            mapResult.put(ORINAME, fileNameValue);
            mapResult.put(PATH, filepath);
            result.add(mapResult);
        } catch (Exception ex) {
            throw new UserException(cmd.getId(), cmd.getErrorid(), "", ex);
        }
        return result;
    }

    /***
     * 写入xls文件
     *
     * @param data
     * @param filePath
     */
    private void writeExcel(Object data, String filePath, String oriName, String fileType) {
        Map<String, Object> oneData;
        List<Map<String, Object>> allData = TypeUtil.changeToListMap(data);
        if (allData.size() == 0) {
            oneData = new HashMap<>();
        } else {
            oneData = allData.get(0);
        }
        // 构建标题
        List<String> title = new ArrayList<>();
        Iterator iterator = oneData.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            title.add(key);
        }
        //sheet 名
        String sheetName = oriName.substring(0, oriName.lastIndexOf("."));
        WriteExcelUtil.writerExcel(filePath, fileType, sheetName, title, allData);
    }

    /***
     * 把字符串的正文写入文件
     *
     * @param content
     * @param filePath
     * @throws Exception
     */
    private void writeStringContentToFile(String content, String filePath) throws Exception {
        // 写入磁盘
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(filePath));
            fileWriter.write(content);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != fileWriter) {
                fileWriter.close();
            }
        }
    }

    /***
     * 写入doc文件
     *
     * @param allData
     * @param filePath
     */
    private void writeDoc(Object allData, String filePath) {

    }

    /***
     * 写入txt文件
     *
     * @param data
     * @param filePath
     */
    private void writeListMapToTxt(Object data, String filePath) throws Exception {
        List<Map<String, Object>> allData;
        Map<String, Object> oneData = null;
        allData = TypeUtil.changeToListMap(data);
        if (allData.size() == 0) {
            oneData = new HashMap<>();
        } else {
            oneData = allData.get(0);
        }
        // 构建标题
        StringBuffer titleBuffer = new StringBuffer();
        List<String> title = new ArrayList<>();
        Iterator iterator = oneData.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            title.add(key);
            titleBuffer.append(key + "\t");
        }
        titleBuffer.append("\r\n");
        //构建正文
        StringBuffer contentBuffer = new StringBuffer();
        for (int i = 0; i < allData.size(); i++) {
            Map<String, Object> map = allData.get(i);
            for (int j = 0; j < title.size(); j++) {
                String name = title.get(j);
                String value = map.get(name) == null ? "" : map.get(name).toString();
                contentBuffer.append(value + "\t");
            }
            contentBuffer.append("\r\n");
        }
        contentBuffer = titleBuffer.append(contentBuffer);
        // 写入磁盘
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(filePath));
            fileWriter.write(contentBuffer.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != fileWriter) {
                fileWriter.close();
            }
        }

    }

    /**
     * 获取空格字符串的长度
     *
     * @param contentLength
     * @return
     */
    private String getSpaceStr(int contentLength, int maxSize) {
        int redictLength = maxSize - contentLength;
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < redictLength; i++) {
            strBuffer.append(" ");
        }
        return strBuffer.toString();
    }

    /***
     * 替换一列
     *
     * @param maxLength
     * @param key
     * @param content
     * @return
     */
    private StringBuffer replacePlaceHolder(int maxLength, String key, StringBuffer content) {
        if (content.indexOf(key) == -1)
            return content;
        int start = content.indexOf(key) + key.length();
        String closeKey = key.substring(0, key.lastIndexOf(PLACEHOLDER)) + ",";
        int end = content.indexOf(closeKey);
        String value = content.substring(start, end);
        int valuelength = Integer.parseInt(value);
        String spaceStr = getSpaceStr(valuelength, maxLength);
        content = content.replace(content.indexOf(key), content.indexOf(closeKey) + closeKey.length(), spaceStr);
        return replacePlaceHolder(maxLength, key, content);
    }
}
