package com.lql.spring.converter;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by StrangeDragon on 2019/7/22 10:13
 * <p>
 * 在spring ioc finishBeanFactoryInitialization()方法中有完成当前上下文的转换服务 “conversionService”
 * <p>
 * 转换器：将前端传递来的参数转换为方法中想要的类型
 * 它用来将前端传过来的参数和后端的 controller 方法上的参数进行绑定的时候用
 **/
@Configuration
public class StringToDateConverter implements Converter<String, Date> {

    String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "HH:mm:ss", "HH:mm"};

    @Override
    public Date convert(String s) {
        try {
            return DateUtils.parseDate(s, parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }


}
