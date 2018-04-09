package com.sfit.autoupgrade.util;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/4/9
 * Time: 21:33
 * Description:日志记录器
 */
public class Logger {
    private Logger() {
    }

    /**
     * 日志输出到控制台
     * @param msg
     */
    public static void log(String msg) {
        System.out.println("[INFO] " + DateUtil.getCurrentTime() + " " + msg);
    }
}
