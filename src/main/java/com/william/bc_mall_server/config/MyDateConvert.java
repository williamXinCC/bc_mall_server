package com.william.bc_mall_server.config;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
* @Author: HMG
* @Date: 2018/10/20
* @Description: 接受前台get传的日期(Date)类型参数特殊处理
*/

public class MyDateConvert implements Converter<String, Date> {

    private final static DateTimeFormatter DATE_TIME_FORMAT_1 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private final static DateTimeFormatter DATE_TIME_FORMAT_2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    private final static DateTimeFormatter DATE_TIME_FORMAT_3 = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public Date convert(String source) {
        Date date = null;
        if (StringUtils.isBlank(source)) {
            return null;
        }
        try {
            date = DATE_TIME_FORMAT_1.parseDateTime(source).toDate();
            return date;
        } catch (Exception e) {
        }
        try {
            date = DATE_TIME_FORMAT_2.parseDateTime(source).toDate();
            return date;
        } catch (Exception e) {
        }
        try {
            date = DATE_TIME_FORMAT_3.parseDateTime(source).toDate();
            return date;
        } catch (Exception e) {
        }
        throw new IllegalArgumentException(source);
    }
}