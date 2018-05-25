package com.sfit.autoupgrade.core;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/5/25
 * Time: 15:58
 * Description:封装响应数据的对象
 */
public class ResponseObject implements ServletResponse {
    private PrintWriter out;

    @Override
    public void setWriter(PrintWriter out) {
        this.out = out;
    }

    @Override
    public PrintWriter getWriter() {
        return this.out;
    }
}
