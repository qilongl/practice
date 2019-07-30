package com.lql.ext;

import com.lql.xml.beans.SysProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author lql
 * @Date 2018/7/23 18:01
 */
@Controller
public class DownLoadController {
    //    private static String filePath = "resources/file";
    public static void main(String[] args) throws Exception {
        Runtime rt=Runtime.getRuntime();
        System.out.println(rt.totalMemory()/64/1024);
//        getFiles("src\\main\\resources\\file");
    }

    @RequestMapping(path = "/downloadByAddress", method = {RequestMethod.GET, RequestMethod.POST})
//    public static ArrayList<File> getFiles(String path) throws Exception {
//        path=System.getProperty("user.dir")+File.separator+path;
//        System.out.println(path);
    //目标集合fileList
//        ArrayList<File> fileList = new ArrayList<File>();
//        File file = new File(path);
//        if (file.isDirectory()) {
//            File[] files = file.listFiles();
//            for (File fileIndex : files) {
//                //如果这个文件是目录，则进行递归搜索
//                if (fileIndex.isDirectory()) {
//                    getFiles(fileIndex.getPath());
//                } else {
//                    //如果文件是普通文件，则将文件句柄放入集合中
//                    fileList.add(fileIndex);
//                }
//            }
//        }
//        return fileList;
//    }
    public void downLoad(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        String path = req.getParameter("path");
        System.out.println(path);
        System.out.println(SysProperties.get("reportAttachPath"));
//        path=System.getProperty("user.dir")+File.separator+path;
        path = SysProperties.get("reportAttachPath") + path;
        File file = new File(path);
//        JSONObject result2 = (JSONObject) JSON.toJSON(rs.getResult());
        String fileName = file.getName();
//        String path = result2.getString("PATH");
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

}
///mnt/nfs/ftp/wbbg/2018/05/6BE690E1BB1D6F61E0532A32A8C0C68C.PDF