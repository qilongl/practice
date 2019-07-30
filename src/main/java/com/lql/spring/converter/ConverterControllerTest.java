package com.lql.spring.converter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by StrangeDragon on 2019/7/22 10:19
 **/
@RestController
public class ConverterControllerTest {

    /**
     * 当方法中有date类型参数需要时，"StringToDateConverter.java" 配置类将前端传递的string类型转换为date类型
     *
     * @param date
     * @return
     */
    @RequestMapping("converter")
    public Object test(Date date) {
        return date.getTime();
    }

    /**
     * 方法没有类型转换时则不转换
     *
     * @param date
     * @return
     */
    @RequestMapping("converter2")
    public Object test2(String date) {
        return date;
    }

    /**
     * StringToBooleanConverter 转换器
     *
     * @param date
     * @return
     */
    @RequestMapping("converter3")
    public Boolean test3(Boolean date) {
        return date;
    }
}
