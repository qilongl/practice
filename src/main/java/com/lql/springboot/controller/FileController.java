package com.lql.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lql.springboot.beans.Attach;
import com.lql.springboot.beans.SysConfigrationBean;
import com.lql.springboot.service.FileService;
import com.lql.util.ResponseResult;
import com.lql.util.StringHelper;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Author lql
 * @Date 2018/7/1 9:29
 */
@RestController
@CrossOrigin
public class FileController {
    @Autowired
    FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);


    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        return name;
    }

    //文件上传相关代码
    @RequestMapping(value = "upload", method = RequestMethod.POST)
//    public String upload(@RequestParam("test") MultipartFile file, HttpServletResponse response) {
//        if (file.isEmpty()) {
//            return "文件为空";
//        }
//        // 获取文件名
//        String fileName = file.getOriginalFilename();
//        logger.info("上传的文件名为：" + fileName);
//        // 获取文件的后缀名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        logger.info("上传的后缀名为：" + suffixName);
//        // 文件上传后的路径
//        String filePath = "E://test//";
//        // 解决中文问题，liunx下中文路径，图片显示问题
//        // fileName = UUID.randomUUID() + suffixName;
//        File dest = new File(filePath + fileName);
//        // 检测是否存在目录
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//            return "上传成功";
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "上传失败";
//    }
    //多文件上传
    public String handleFileUpload(HttpServletRequest request) throws Exception {
        String type = StringHelper.toString(request.getParameter("type"));
        type = (type == "" ? "10" : type);
        String filePath = SysConfigrationBean.getAttachmentPath() + File.separator;
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        Attach attach = new Attach();
        String result = "";
//        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                //构建参数
                String fileName = file.getOriginalFilename();//文件名
                String objId = StringHelper.getUUID();
                String objType = fileName.substring(fileName.lastIndexOf(".") + 1);
                String fileTruthName = objId + "." + objType;//磁盘存储的文件名
                String path = filePath + fileTruthName;//磁盘的全路径
                attach.setObjId(objId);
                attach.setObjName(fileName);
                attach.setAttachSize(String.valueOf(file.getSize()));
                attach.setAttachType(objType);
                attach.setPath(path);
                attach.setType(type);
                result = fileService.attachUpload(attach);
                logger.info("文件大小：" + String.valueOf((file.getSize() / 1024) + "KB" + " (" + file.getSize() + "byte)"));
                logger.info("文件内容类型：" + file.getContentType());
                logger.info("文件名称：" + file.getOriginalFilename());
                try {
                    // 文件上传后的路径
//                    String filePath = "E://test//";
                    // 解决中文问题，liunx下中文路径，图片显示问题
                    //存储在磁盘上的附件名称，用uuid标明唯一性 (文件名=UUID+文件类型)
                    //文件存储的绝对路径地址
                    File dest = new File(path);
                    // 检测是否存在目录
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    file.transferTo(dest);//将上传文件写到指定的文件上
                    return result;
//                    byte[] bytes = file.getBytes();
//                    stream = new BufferedOutputStream(new FileOutputStream(
//                            new File(file.getOriginalFilename())));
//                    stream.write(bytes);
//                    stream.close();
                } catch (Exception e) {
//                    stream = null;
                    return "You failed to upload " + i + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }
        }
        return "upload successful";
    }


    @RequestMapping(value = "/downLoad", method = {RequestMethod.GET, RequestMethod.POST})
    public void downLoad(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        ResponseResult rs = null;
        String objId = req.getParameter("OBJID");
        String result = fileService.downLoad(objId);
        rs = JSON.parseObject(result, new TypeReference<ResponseResult>() {
        });
        JSONObject result2 = (JSONObject) JSON.toJSON(rs.getResult());
        String fileName = result2.getString("OBJNAME");
        String path = result2.getString("PATH");
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
//        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);//中文文件名会乱码   (乱码为----)
        res.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(path)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    /**
     * 附件列表
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "/attachList", method = {RequestMethod.GET, RequestMethod.POST})
    public String attachList(HttpServletRequest req, HttpServletResponse res,
                             @RequestParam(value = "jsonParams", required = false) String jsonParams) {
        ResponseResult rs = null;
        String result = fileService.findAttachList(jsonParams);
        return result;
    }
}

