package com.lql.util.verification.service;

import com.alibaba.fastjson.JSON;
import com.lql.util.verification.bean.RandomGraphic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lql
 * @date 2017/12/29 13:59
 * @describe
 */
@Service
public class RandomGraphicService {
    private static Logger logger = LoggerFactory.getLogger(RandomGraphicService.class);

    public static String verification() throws Exception {
        Map<String,Object> result = new HashMap<String, Object>();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String str = RandomGraphic.createInstance(4).drawInputstr(4, RandomGraphic.GRAPHIC_PNG, output);
        logger.info("----------------验证码:" + str);
        byte[] captcha = output.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();

        String imagestr = encoder.encode(captcha);// 返回Base64编码过的字节数组字符串
        result.put("image_base64", imagestr);
        result.put("result",str);
        return JSON.toJSONString(result);
//        String path = "D:/myimg.png";
//        String path2 = "D:/myimg2.png";
//        byte[] data = captcha;
//        if (data.length < 3 || path.equals("")) return "";
//        try {
//            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
//            imageOutput.write(data, 0, data.length);
//            imageOutput.close();
//            System.out.println("Make Picture success,Please find image in " + path);
//        } catch (Exception ex) {
//            System.out.println("Exception: " + ex);
//            ex.printStackTrace();
//        }

//        BASE64Decoder decoder = new BASE64Decoder();
//        try {
//            // Base64解码
//            byte[] bytes = decoder.decodeBuffer(imagestr);
//            for (int i = 0; i < bytes.length; ++i) {
//                if (bytes[i] < 0) {// 调整异常数据
//                    bytes[i] += 256;
//                }
//            }
//            // 生成jpeg图片
//            OutputStream out = new FileOutputStream(path2);
//            out.write(bytes);
//            out.flush();
//            out.close();
//        } catch (Exception e) {
//        }
//        System.out.println(RandomGraphic.createInstance(4).drawAlpha(RandomGraphic.GRAPHIC_JPEG, new FileOutputStream("D:/myimg2.png")));

    }

}

