package com.lql.springmvc.controller;

import com.lql.SpringbootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author lql
 * @Date 2018/7/23 11:26
 */
@ControllerAdvice
@ResponseBody
public class ControllerError {
//    @ExceptionHandler
//    public String exceptionHandler(Exception ex) {
//        ModelAndView mv = new ModelAndView("error");
//        mv.addObject("exception", ex);
//        System.out.println("in testControllerAdvice");
//        return "你出错啦";
//    }

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();

        Test test= new Test();
        test.setAddress("fefe");
        test.name="fe";
        System.out.println(test.toString());
    }
}
