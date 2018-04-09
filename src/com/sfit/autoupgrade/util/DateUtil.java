package com.sfit.autoupgrade.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/4/9
 * Time: 22:14
 * Description:时间相关工具类
 */
public class DateUtil {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    private DateUtil() {
    }

    /**
     * 获取系统当前时间
     * @return String [yyyy-MM-dd HH:mm:ss SSS]
     */
    public static String getCurrentTime() {
        return dateFormat.format(new Date());
    }
}
