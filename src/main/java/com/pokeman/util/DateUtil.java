package com.pokeman.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ycy
 * @since 2017/7/31
 */
public final class DateUtil {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前时间 String 格式
     * @return
     */
    public static String getCurrentTime(){
        Date date = new Date();
        String dateTime = sdf.format(date);
        return dateTime;
    }

}
