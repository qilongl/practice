package com.lql.util;


import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xhb
 * @modity xl
 * @date 2010-10-23 下午01:52:35
 */
@SuppressWarnings({"unchecked"})
public class DateUtil {
    private static Log log = LogFactory.getLog(DateUtil.class);

    public static final String PATTERN_FULLM_TIME = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String PATTERN_MIDUM_TIME = "yyyy-MM-dd";
    public static final String PATTERN_MINUTE_TIME = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_FULL_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String SQL_FULL_TIME = "yyyy-MM-dd hh24:mi:ss";
    public static final String PATTERN_MIDUM_DATE = "yyyyMMdd";
    public static final FastDateFormat DF_FULL = FastDateFormat.getInstance(PATTERN_FULL_TIME);
    public static final FastDateFormat DF_MIDUM = FastDateFormat.getInstance(PATTERN_MIDUM_TIME);

    /*
    加日期
     */
    public static Date datePlus(Date oldDate, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(oldDate);
        c.add(Calendar.DAY_OF_MONTH, i);// 今天+i天

        Date newDate = c.getTime();
        return newDate;
    }

    /**
     * 字符串 转化 时间
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) {
        try {
            return DateUtils.parseDate(dateStr, new String[]{PATTERN_FULLM_TIME, PATTERN_FULL_TIME, PATTERN_MIDUM_TIME});
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_FULLM_TIME);
        String result = sdf.format(new Date());
        //20190603120000000
        System.out.println(result);
    }

    /**
     * 获得当前时间字符串
     *
     * @return
     */
    public static String getDateString() {
        return dateToString(new Date(), PATTERN_MIDUM_TIME);
    }

    /**
     * 获得指定时间字符串
     *
     * @param d
     * @return
     */
    public static String getDateString(Date d) {
        return dateToString(d, PATTERN_MIDUM_TIME);
    }

    /**
     * 获得指定时间指定格式的字符串
     *
     * @param d
     * @param format
     * @return
     */
    public static String dateToString(Date d, String format) {
        return FastDateFormat.getInstance(format).format(d);
    }

    /**
     * 活动当前时间指定格式的字符串
     *
     * @param format
     * @return
     */
    public static String dateToString(String format) {
        return FastDateFormat.getInstance(format).format(new Date());
    }

    /**
     * 转换为网页显示格式
     *
     * @param d
     * @return
     */
    public static String toSimpleDateString(Date d) {
        if (d == null) {
            return null;
        }
        long old = d.getTime();
        long now = new Date().getTime();
        int m = (int) ((now - old) / DateUtils.MILLIS_PER_MINUTE);
        if (m < 60) {
            return (m == 0 ? 1 : m) + "分钟前";
        }
        int h = (int) ((now - old) / DateUtils.MILLIS_PER_HOUR);
        if (h < 24) {
            return h + "小时前";
        }
        int day = (int) ((now - old) / DateUtils.MILLIS_PER_DAY);
        if (day <= 3) {
            return day + "天前";
        }
        return dateToString(d, "yyyy-MM-dd HH:mm");
    }


    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 基于当前时间的时间区间
     *
     * @param field  幅度类型 Calendar.WEEK_OF_YEAR   Calendar.WEEK_OF_MONTH ...
     * @param time   增减时间幅度 +1，-1
     * @param format 格式 yyyy-MM-dd  yyyy-MM-dd hh:mi:ss ...
     * @return "2018-01-22，2018-03-11"
     */
    public static String timeHandle(int field, int time, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, time);
        String finalVal1 = null;
        if (time >= 0) {
            finalVal1 = dateToString(new Date(), format) + "," + dateToString(calendar.getTime(), format);
        } else {
            finalVal1 = dateToString(calendar.getTime(), format) + "," + dateToString(new Date(), format);
        }
        return finalVal1;
    }


    /**
     * 2019-03-21变更
     *
     * @param field
     * @param time
     * @return
     */
    public static String timeHandle2(int field, int time) throws ParseException {
        DateFormat dateFormat = null;
        String format = null;
        String finalVal1 = null;
        int aimMis = 0;
        int dayMis = 1000 * 60 * 60 * 24;//一天的毫秒-1
        int minMis = 1000 * 60;
        int secondMis = 1000;
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, time);
        switch (field) {
            case Calendar.DAY_OF_MONTH:
                format = PATTERN_MIDUM_TIME;
                aimMis = dayMis;
                break;
            case Calendar.MINUTE:
                format = PATTERN_MINUTE_TIME;
                aimMis = minMis;
                break;
            case Calendar.SECOND:
                format = PATTERN_FULL_TIME;
                aimMis = secondMis;
                break;
            default:
                throw new UnsupportedOperationException("时间转换错误，暂不支持该类型：" + field);
        }
        dateFormat = new SimpleDateFormat(format);
        Date airDate = dateFormat.parse(dateFormat.format(calendar.getTime()));
        Date nowDate = dateFormat.parse(dateFormat.format(new Date().getTime()));
        long startMis = airDate.getTime();
        long nowEndMis = nowDate.getTime() + aimMis - 1;
        finalVal1 = dateToString(new Date(startMis), PATTERN_FULL_TIME) + "," + dateToString(new Date(nowEndMis), PATTERN_FULL_TIME);
        return finalVal1;
    }

    /**
     * 获取指定时间
     *
     * @param field
     * @param time
     * @param format
     * @return
     */
    public static String getAimTimeHandle(int field, int time, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, time);
        return dateToString(calendar.getTime(), format);
    }

}
