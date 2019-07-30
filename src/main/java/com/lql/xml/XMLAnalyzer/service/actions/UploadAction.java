package com.lql.xml.XMLAnalyzer.service.actions;

import com.lql.util.DateFormater;
import com.lql.util.StringHelper;
import com.lql.xml.XMLAnalyzer.beans.Action;
import com.lql.xml.XMLAnalyzer.beans.BusiConfig;
import com.lql.xml.XMLAnalyzer.beans.UserException;
import com.lql.xml.XMLAnalyzer.util.ExpressionUtil;
import com.lql.xml.XMLAnalyzer.util.TypeUtil;
import com.lql.xml.beans.SysProperties;
import net.sf.json.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lql on 2017/5/25.
 */
public class UploadAction implements Serializable {
    public final static String ORINAME = "ORINAME";// 文件原始名称
    public final static String IDNAME = "IDNAME";//生成的随机名称
    public final static String DATE = "DATE";// 创建时间
    public final static String SIZE = "SIZE";// 创建时间
    public final static String PATH = "PATH";// 绝对路径
    public final static String TYPE = "TYPE";// 文件类型
    public final static String SOURCE = "SOURCE";//附件原文
    private BusiConfig busiConfig;

    public UploadAction(BusiConfig busiConfig) {
        this.busiConfig = busiConfig;
    }

    //--------------------------------------------------文件上传-------------------------------------------------------------//
    public Object uploadCommand(Action cmd, Map<String, List<Map<String, byte[]>>> fileMap, JSONObject inParams) throws Exception {
        try {
            if (cmd.isRequired() && !fileMap.containsKey(cmd.getPropertyname())) {
                throw new NullPointerException(cmd.getPropertyname() + "是必填字段，不可以为空！");
            }
            /***
             * 定义返回结果集
             */
            List<Map> mapList = new ArrayList<>();
            // 取出附件
            String propertyName = getPropertyName(cmd, inParams);
            List<Map<String, byte[]>> filesList = fileMap.get(propertyName);

            if (null == filesList) {
                return mapList;
            }
            // 定义存储属性的集合
            List<String> orgNameList = new ArrayList<>();
            List<String> genIdNameList = new ArrayList<>();
            List<String> dateList = new ArrayList<>();
            List<Long> sizeList = new ArrayList<>();
            List<String> pathList = new ArrayList<>();
            List<String> typeList = new ArrayList<>();
            List<byte[]> sourceList = new ArrayList<>();
            // 遍历附件集合
            for (int i = 0; i < filesList.size(); i++) {
                Map<String, byte[]> map = filesList.get(i);
                Iterator it = map.keySet().iterator();
                //原始文件名称
                String orgName = it.next().toString();
                //新生成的文件名称
                String genIdName = StringHelper.getUUID();
                byte[] fileBytes = map.get(orgName);
                StringBuffer filepath = null;
                if (StringHelper.isNotNull(cmd.getBasesavepath())) {
                    String basepath = getBasePath(cmd, inParams);
                    filepath = new StringBuffer(basepath);
                } else {
                    filepath = new StringBuffer(SysProperties.getAttachmentPath());
                }
                /****
                 *解析存储路径
                 */
                String savePath = cmd.getSavepath();
                if (StringHelper.isNotNull(savePath)) {
                    //解析出保存路径:#{}或者字符串
                    String savepath = getSavePath(cmd, inParams);
                    filepath = filepath.append(File.separator + savepath);
                    filepath = filepath.append(File.separator);
                }
                File dir = new File(filepath.toString());
                //校验人工输入的路径是否存在,如果不存在则创建
                if (!dir.exists()) {
//                    throw new UnknownObjectException(cmd.getContent() + " \n保存路径" + filepath.toString() + "不存在!");
                    dir.mkdirs();
                }
                // 产生日期目录
                Calendar calendar = Calendar.getInstance();
                if (cmd.isdatedir()) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
                    String date = sf.format(calendar.getTime());
                    sf = new SimpleDateFormat("yyyyMMdd");
                    String day = sf.format(calendar.getTime());
                    filepath = filepath.append(File.separator + date + File.separator + day);
                }
                dir = new File(filepath.toString());
                if (!dir.exists())
                    dir.mkdirs();
                // 拼接完整文件路径结构
                String filename = null;
                if (cmd.isUsegenname()) {
                    filename = genIdName;
                } else {
                    filename = orgName;
                }
                filepath = filepath.append(File.separator + filename);
                /***
                 * 写入磁盘
                 */
                File file = new File(filepath.toString());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
                stream.write(fileBytes);
                stream.close();
                /***
                 * 提取属性到列表中
                 */
                orgNameList.add(orgName);
                genIdNameList.add(filename);
                pathList.add(file.getAbsolutePath());
                sizeList.add(file.length());
                calendar.setTimeInMillis(file.lastModified());
                dateList.add(DateFormater.yyyyMMddHHmmss(calendar.getTime()));
                String fileName = orgName;
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                typeList.add(fileType);
                sourceList.add(fileBytes);
            }
            /***
             * 构建返回结果集
             */
            for (int i = 0; i < orgNameList.size(); i++) {
                Map<String, Object> map = new HashMap();
                map.put(UploadAction.ORINAME, orgNameList.get(i));
                map.put(UploadAction.IDNAME, genIdNameList.get(i));
                map.put(UploadAction.DATE, dateList.get(i));
                map.put(UploadAction.SIZE, sizeList.get(i));
                map.put(UploadAction.PATH, pathList.get(i));
                map.put(UploadAction.TYPE, typeList.get(i));
                map.put(UploadAction.SOURCE, sourceList.get(i));
                mapList.add(map);
            }
            return mapList;
        } catch (Exception ex) {
            throw new UserException(cmd.getId(), cmd.getErrorid(), "上传附件异常;", ex);
        }
    }

    /***
     * 解析出保存字符串
     *
     * @param action
     * @param inParams
     * @return
     */
    private String getSavePath(Action action, JSONObject inParams) {
        String params = action.getParams();
        String savePath = action.getSavepath();
        savePath = getValue(action, inParams, params, savePath);
        return savePath;
    }

    /***
     * 从以上结果集中取出第一个结果
     *
     * @param action
     * @param params
     * @param inParams
     * @return
     */
    public Map<String, Object> getOneResult(Action action, String params, JSONObject inParams) {
        List<Map<String, Object>> list = TypeUtil.changeToListMap(busiConfig.getParamsFromInParams_CmdResult(params, inParams));
        if (list.size() != 1) {
            throw new UnsupportedOperationException(action.getContent() + "\nparams指定的入参大小不为1,实际是:" + list.size());
        }
        return list.get(0);
    }

    /***
     * 获取附件名称
     *
     * @param action
     * @param inParams
     * @return
     */
    private String getPropertyName(Action action, JSONObject inParams) {
        String params = action.getParams();
        String propertyname = action.getPropertyname();
        propertyname = getValue(action, inParams, params, propertyname);
        return propertyname;
    }

    /***
     * 获取存储根目录
     *
     * @param action
     * @param inParams
     * @return
     */
    private String getBasePath(Action action, JSONObject inParams) {
        String params = action.getParams();
        String basesavepath = action.getBasesavepath();
        basesavepath = getValue(action, inParams, params, basesavepath);
        return basesavepath;
    }

    public String getValue(Action action, JSONObject inParams, String params, String valueContent) {
        String patten1 = "[\\s\\S]*[#][{][\\s\\S]*[}][\\s\\S]*";
        StringBuffer savepathBuffer = new StringBuffer(valueContent);
        if (StringHelper.isNotNull(valueContent)) {
            if (ExpressionUtil.machExpression(patten1, valueContent) && valueContent.indexOf(".") == -1)//#{savepath}
            {
                String savepathName = valueContent.substring(valueContent.indexOf("#{") + 2, valueContent.indexOf("}"));
                if (!StringHelper.isNotNull(params)) {
                    throw new UnsupportedOperationException(action.getContent() + "\n在参数中只配置入参属性的情况下，params定义的入参名称不可以空!");
                }
                Map<String, Object> mapParams = getOneResult(action, params, inParams);
                String savePathValue = mapParams.get(savepathName).toString();
                savepathBuffer = savepathBuffer.replace(savepathBuffer.indexOf("#{"), savepathBuffer.indexOf("}") + 1, savePathValue);
            } else if (ExpressionUtil.machExpression(patten1, valueContent) && valueContent.indexOf(".") != -1)//#{addparams.savepath}
            {
                String paramName = savepathBuffer.substring(savepathBuffer.indexOf("#{") + 2, savepathBuffer.indexOf("."));
                String paramsAttr = savepathBuffer.substring(savepathBuffer.indexOf(".") + 1, savepathBuffer.indexOf("}"));
                Map<String, Object> mapParams = getOneResult(action, paramName, inParams);
                String savePathValue = mapParams.get(paramsAttr).toString();
                savepathBuffer = savepathBuffer.replace(savepathBuffer.indexOf("#{"), savepathBuffer.indexOf("}") + 1, savePathValue);
            } else if (valueContent.indexOf("#") == -1 && valueContent.indexOf("$") == -1) {
                //savePath为直接引用字符串
            } else {
                throw new UnsupportedOperationException(action.getContent() + "\n " + valueContent + "配置的不合规! 例如：#{a.b}或者#{a}或者abc");
            }
        }
        return savepathBuffer.toString();
    }


    public static void main(String args[]) {
        String patten1 = "[\\s\\S]*[#][{][\\s\\S]*[}][\\s\\S]*";
        String patten2 = "[\\s\\S]*[#][{][\\s\\S]*[.][\\s\\S]*[}][\\s\\S]*";
        System.out.println(ExpressionUtil.machExpression(patten1, "f7d87f8d#{au.yub}fdfd"));
    }
}
