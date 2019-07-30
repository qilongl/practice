package com.lql.springboot.controller;

import com.lql.springboot.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author lql
 * @Date 2018/7/1 20:40
 */
@RestController
@CrossOrigin
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    /**
     * 登录
     * @param req
     * @param response
     * @return
     */
    @RequestMapping(value="/login",method = {RequestMethod.POST, RequestMethod.GET})
    public String login(@RequestParam(value = "jsonParams", required = true) String jsonParams,
                        HttpServletRequest req, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String result = sysUserService.login(jsonParams);
        return result;
    }

    /**
     * 刚换用户头像
     * @param req
     * @param response
     * @return
     */
    @RequestMapping(value="/changeUserPhoto",method = {RequestMethod.POST, RequestMethod.GET})
    public String changeUserPhoto(@RequestParam(value = "jsonParams", required = true) String jsonParams,
                        HttpServletRequest req, HttpServletResponse response) {
        String result = sysUserService.changeUserPhoto(jsonParams);
        return result;
    }
}
