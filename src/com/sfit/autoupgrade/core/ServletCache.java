package com.sfit.autoupgrade.core;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/8/7
 * Time: 14:30
 * Description:Servlet对象缓存池
 */
public class ServletCache {
    private static Map<String, Servlet> servletMap = new HashMap<>();

    public static void put(String urlPattern, Servlet servlet) {
        servletMap.put(urlPattern, servlet);
    }

    public static Servlet get(String urlPattern) {
        return servletMap.get(urlPattern);
    }
}
