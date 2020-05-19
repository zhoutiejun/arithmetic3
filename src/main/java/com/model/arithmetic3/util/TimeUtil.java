package com.model.arithmetic3.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/3/12 0012 下午 14:25
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/3/12 0012 下午 14:25
 */
public class TimeUtil {


    public static String getOriginTimeStr(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public static Date getOriginDate(String startTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(startTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误");
        }
    }
}
