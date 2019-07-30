package com.lql.spring.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class StringToBooleanConverter implements Converter<String, Boolean> {

    @Override
    public Boolean convert(String s) {
        return !Boolean.parseBoolean(s);
    }
}