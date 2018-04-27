package org.sfit.oa.servlet;

import javax.servlet.Servlet;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/4/24
 * Time: 15:02
 * Description:处理登录业务的类
 */
public class LoginServlet implements Servlet{
    @Override
    public void service(){
        System.out.println("正在验证身份，请稍后...");
    }
}
