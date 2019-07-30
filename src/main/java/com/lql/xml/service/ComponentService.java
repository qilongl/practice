package com.lql.xml.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lql.util.ResponseResult;
import com.lql.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lql on 2017/5/26.
 */
@Service
public class ComponentService {
    @Autowired
    RestTemplate restTemplate;


    public static String tmpPath = "tmp";
    private static Logger logger = LoggerFactory.getLogger(ComponentService.class);

    @Value("${tmp.dir:c:/tmp}")
    private String tmpDir;

    //    @HystrixCommand(fallbackMethod = "fallback")
    public ResponseResult service(String jsonParams, MultiValueMap<String, MultipartFile> fileMap, JSONObject systemJsonObject,String url) throws Exception {
        ResponseResult rr = new ResponseResult();
        /***
         * 上传文件过程中产生的临时文件集合
         */
        List<File> tmpFiles = new ArrayList<>();
        /****
         * 附件对应的name属性<file name=""></file>
         */
        List<String> keys = new ArrayList<>();
        /***
         * 原始文件名称
         */
        List<String> fileNames = new ArrayList<>();
        /***
         * 参数载体
         */
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        if (fileMap != null) {
            Iterator iterator = fileMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                LinkedList<MultipartFile> ff = (LinkedList<MultipartFile>) fileMap.get(key);
                for (int i = 0; i < ff.size(); i++) {
                    MultipartFile file = ff.get(i);
                    if (file.getOriginalFilename().equals("")) continue;
                    FileSystemResource fileSystemResource;
                    try {
                        fileSystemResource = changeMultiFileToFSR(file);
                    } catch (Exception e) {
                        rr.setMsg("原始文件:" + file.getOriginalFilename() + ",上传错误:" + e.getMessage());
                        rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
                        return rr;
                    }
                    param.add("jarFiles", fileSystemResource);
                    keys.add(key);
                    fileNames.add(file.getOriginalFilename());
                    tmpFiles.add(fileSystemResource.getFile());
                }
            }
        }
        param.add("fileNames", JSON.toJSONString(fileNames).toString());
        param.add("keys", JSON.toJSONString(keys).toString());
        param.add("jsonParams", jsonParams);
        param.add("systemJsonObject", systemJsonObject.toString());
        /****
         * 调用权限服务
         */
//        String rs = restTemplate.postForObject("http://service-expand/expandService", param, String.class);
        /***
         * 调用服务端
         */
        String rs = restTemplate.postForObject(url, param, String.class);
        if (!StringHelper.isNotNull(rs)) {
            rr.setMsg("未收到组件服务(Component)响应信息,请联系管理员!");
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
        } else {
            rr = StringHelper.getRRFromStream(rs);
        }
        /***
         * 清理临时文件
         */
        removeTmpFiles(tmpFiles);
        return rr;
    }

    /***
     * 重新加载xml功能
     *
     * @param key
     * @return
     * @throws Exception
     */
    public ResponseResult reloadFunction(String key) throws Exception {
        ResponseResult rr = new ResponseResult();

        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("key", key);
        /***
         * 调用服务端
         */
        String rs = restTemplate.postForObject("http://service-component/extendService/reloadFunction", param, String.class);
        if (!StringHelper.isNotNull(rs)) {
            rr.setMsg("未收到组件服务(Component)响应信息,请联系管理员!");
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
        } else {
            rr = StringHelper.getRRFromStream(rs);
        }
        return rr;
    }

    /***
     * 转换请求request中的multipartFile 成 FileSystemResource
     *
     * @param multipartFile
     * @return
     */
    private FileSystemResource changeMultiFileToFSR(MultipartFile multipartFile) throws Exception {
        FileSystemResource fileSystemResource;
        File dir = new File(tmpDir);
        if (!dir.exists())
            dir.mkdirs();
        String newFileName = tmpDir + File.separator + StringHelper.getUUID();
        File newFile = new File(newFileName);
        multipartFile.transferTo(newFile);
        fileSystemResource = new FileSystemResource(newFile);
        return fileSystemResource;
    }

    /***
     * 清除产生的临时文件
     *
     * @param tmpList
     */
    private void removeTmpFiles(List<File> tmpList) {
        for (int i = 0; i < tmpList.size(); i++) {
            File file = tmpList.get(i);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public ResponseResult fallback(String jsonParams, MultiValueMap<String, MultipartFile> fileMap, JSONObject systemJsonObject) {
        ResponseResult rs = new ResponseResult();
        rs.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
        rs.setMsg("很遗憾,出错了！");
        return rs;
    }
}
