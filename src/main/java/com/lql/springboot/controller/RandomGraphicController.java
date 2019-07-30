package com.lql.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.lql.util.JsonValueFilter;
import com.lql.util.OutFormater;
import com.lql.util.ResponseResult;
import com.lql.util.verification.service.RandomGraphicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lql
 * @date 2017/12/29 14:47
 * @describe 获取验证码图片 以base64返还
 */
@CrossOrigin
@RestController
public class RandomGraphicController {
    private final static Logger logger = LoggerFactory.getLogger(RandomGraphicController.class);

    @RequestMapping(value = "/getVerificationImage", method = {RequestMethod.POST, RequestMethod.GET})
    public String getVerificationImage() throws Exception {
        ResponseResult rr = new ResponseResult();
        try {
            String result = RandomGraphicService.verification();
            if (result != null && !"".equalsIgnoreCase(result)) {
                logger.info("获取图片验证码成功!");
                rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
                rr.setMsg("获取图片验证码成功!");
                rr.setResult(JSON.parse(result));
            } else {
                logger.info("获取图片验证码失败!");
            }
        } catch (Exception ex) {
            logger.info(OutFormater.stackTraceToString(ex));
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rr.setMsg("获取图片验证码失败!" + OutFormater.stackTraceToString(ex));
            rr.setResult(null);
        }
        return JSON.toJSONString(rr, JsonValueFilter.changeNullToString());
    }

    @RequestMapping(value = "/getVerifyCode", method = {RequestMethod.POST, RequestMethod.GET})
    public String getVerifyCode() throws Exception {
        ResponseResult rr = new ResponseResult();
        try {
            String result = RandomGraphicService.verification();
            if (result != null && !"".equalsIgnoreCase(result)) {
                logger.info("获取图片验证码成功!");
                rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_SUCCESS);
                rr.setMsg("获取图片验证码成功!");
                rr.setResult(JSON.parse(result));
            } else {
                logger.info("获取图片验证码失败!");
            }
        } catch (Exception ex) {
            logger.info(OutFormater.stackTraceToString(ex));
            rr.setStatusCode(ResponseResult.RESULT_STATUS_CODE_ERROR);
            rr.setMsg("获取图片验证码失败!" + OutFormater.stackTraceToString(ex));
            rr.setResult(null);
        }
        return JSON.toJSONString(rr, JsonValueFilter.changeNullToString());
    }

}
