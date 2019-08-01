package com.lql.xml.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.lql.SpringbootApplication;
import com.lql.util.OutFormater;
import com.lql.util.ResponseResult;
import com.lql.util.StringHelper;
import com.lql.xml.XMLAnalyzer.service.impl.BusiService;
import com.lql.xml.XMLAnalyzer.util.ObjectFormater;
import com.lql.xml.db.DPFactory;
import com.lql.xml.db.DPService;
import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lql on 2017/5/25.
 */
@RestController
@Scope("prototype")
public class ExtendController {
    private static Logger logger = LoggerFactory.getLogger(ExtendController.class);

    @Autowired
    BusiService busiService;

    /****
     * 服务接口
     */
    @ResponseBody
    @RequestMapping(value = "/extendService", method = RequestMethod.POST)
    public String exec(String jsonParams, String fileNames, String keys, String systemJsonObject, MultipartFile... jarFiles) {
        /***
         * 构建参数,转换附件成字节流
         */
        ResponseResult rr = new ResponseResult();
        Map<String, List<Map<String, byte[]>>> filesMap = new HashedMap();
        try {
            if (StringHelper.isNotNull(fileNames)) {
                JSONArray fileNameJSonArray = JSONArray.fromObject(fileNames);
                JSONArray keysJSONArray = JSONArray.fromObject(keys);
                change2FilesMap(keysJSONArray, filesMap, fileNameJSonArray, jarFiles);
            }
            // 调用服务、执行
            rr = busiService.exec(jsonParams, filesMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            rr.setMsg(getClass().getName() + "服务执行异常!" + OutFormater.stackTraceToString(ex));
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
        }
        /****
         * 响应
         */
        byte[] bytes = new byte[10];
        try {
            bytes = ObjectFormater.toByteArray(rr);
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getStackTrace());
        }
        return new BASE64Encoder().encode(bytes);
    }

    /***
     * 导出xml的结果集入excel
     *
     * @param jsonParams
     * @param fileNames
     * @param keys
     * @param systemJsonObject
     * @param jarFiles
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exportService", method = RequestMethod.POST)
    public String export(String jsonParams, String fileNames, String keys, String systemJsonObject, MultipartFile... jarFiles) {
        /***
         * 构建参数,转换附件成字节流
         */
        ResponseResult rr = new ResponseResult();
        Map<String, List<Map<String, byte[]>>> filesMap = new HashedMap();
        try {
            //附件格式转换
            if (StringHelper.isNotNull(fileNames)) {
                JSONArray fileNameJSonArray = JSONArray.fromObject(fileNames);
                JSONArray keysJSONArray = JSONArray.fromObject(keys);
                change2FilesMap(keysJSONArray, filesMap, fileNameJSonArray, jarFiles);
            }
            // 调用服务、执行
//            rr = busiService.export(jsonParams, filesMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            rr.setMsg(getClass().getName() + "服务执行异常!" + OutFormater.stackTraceToString(ex));
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
        }
        /****
         * 响应
         */
        byte[] bytes = new byte[10];
        try {
            bytes = ObjectFormater.toByteArray(rr);
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getStackTrace());
        }
        return new BASE64Encoder().encode(bytes);
    }

    /***
     * 重新加载业务功能
     *
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/extendService/reloadFunction", method = RequestMethod.POST)
    public String reloadFunction(String key) {
        ResponseResult rr = new ResponseResult();
        try {
            busiService.reloadFunction(key);
            rr.setMsg(key + "重载成功");
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getStackTrace());
            rr.setMsg(getClass().getName() + "业务文件" + key + "重载失败!" + OutFormater.stackTraceToString(e));
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
        }
        /****
         * 响应
         */
        byte[] bytes = new byte[10];
        try {
            bytes = ObjectFormater.toByteArray(rr);
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getStackTrace());
        }
        return new BASE64Encoder().encode(bytes);
    }


    /****
     * 登录入口
     *
     * @param jsonParams
     * @return
     */
    @ResponseBody
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String exec(String jsonParams) {
        ResponseResult rr = new ResponseResult();
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonParams);
        String token = jsonObject.getString("token");
        String userName = jsonObject.getString("username");
        String pwd = jsonObject.getString("pwd");


        byte[] bytes = new byte[10];
        try {
            bytes = ObjectFormater.toByteArray(rr);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return new BASE64Encoder().encode(bytes);
    }

    /****
     * 把附件转成字节流
     *
     * @param keysJSONArray
     * @param filesMap
     * @param fileNameJSonArray
     * @param jarFiles
     * @throws Exception
     */
    private void change2FilesMap(JSONArray keysJSONArray, Map<String, List<Map<String, byte[]>>> filesMap, JSONArray fileNameJSonArray, MultipartFile... jarFiles) throws Exception {
        if (null == jarFiles)
            return;
        for (int i = 0; i < jarFiles.length; i++) {
            MultipartFile file = jarFiles[i];
            String fileName = fileNameJSonArray.getString(i);
            String key = keysJSONArray.getString(i);
            if (filesMap.containsKey(key)) {
                Map<String, byte[]> map = new HashedMap();
                List<Map<String, byte[]>> fileList = filesMap.get(key);
                map.put(fileName, file.getBytes());
                fileList.add(map);
                filesMap.put(key, fileList);
            } else {
                Map<String, byte[]> map = new HashedMap();
                List<Map<String, byte[]>> fileList = new ArrayList<>();
                map.put(fileName, file.getBytes());
                fileList.add(map);
                filesMap.put(key, fileList);
            }
        }
    }


    @RequestMapping(value = "/toAll", method = RequestMethod.GET)
    public String testAll() {
        DPService dpService = DPFactory.createDPService(SpringbootApplication.ctx);
        DruidDataSource dataSource = (DruidDataSource) dpService.getDataSource();
        System.out.println(dataSource.getDbType());
        String sql = "select ID from sys_user ";
        dpService.startTransaction();
        List<Map<String, Object>> list = dpService.select(sql);
        //dpService.commit();
        return list.size() + "";
    }


    public static void main(String args[]) {
        try {
//            throw new UnsupportedOperationException("不支持此格式!");
            List<String> list = new ArrayList<>();
            list.add("123");
            String str = list.get(1);
        } catch (Exception ex) {
            StringBuffer stringBuffer = new StringBuffer();
            //自定义异常输出格式
            stringBuffer.append(ex.toString() + "\n");
            for (int i = 0; i < ex.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = ex.getStackTrace()[i];
                stringBuffer.append("\tat:" + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")\n");
//                System.out.println(stackTraceElement.getClassName()+"行:"+stackTraceElement.getLineNumber()+"方法:"+stackTraceElement.getMethodName());
            }
            System.out.println(stringBuffer);
            //ex.printStackTrace();
        }
    }


}