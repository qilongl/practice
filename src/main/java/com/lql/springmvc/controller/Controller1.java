package com.lql.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author lql
 * @Date 2018/7/23 11:08
 */
@Controller
@ResponseBody
public class Controller1 {
    @RequestMapping("/person")
    public String autoPipei(String name,int age){
        System.out.println(name +" 今年 "+age +" 岁!");
        return "你大爷还是你大爷";
    }

    @RequestMapping("/error123")
    public String error(){
        int result=5/0;
        return "你大爷还是你大爷";
    }

}
