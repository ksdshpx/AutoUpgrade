package org.sfit.oa.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/6/21
 * Time: 15:50
 * Description:保存用户处理类
 */
public class UserSaveServlet implements Servlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) {
        //获取页面请求的参数
        String username = request.getParameterValue("username");
        String gendar = request.getParameterValue("gendar");
        String[] interest = request.getParameterValues("interest");
        StringBuilder interests = new StringBuilder();
        for (String interestValue : interest) {
            interests.append(interestValue).append(" ");
        }

        //获取响应流对象
        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print("<head>");
        out.print("<title>用户信息</title>");
        out.print("<meta charset=\"UTF-8\"/>");
        out.print("</head>");
        out.print("<body>");
        out.print("用户名:" + username +"<br/>");
        out.print("性别:" + gendar +"<br/>");
        out.print("兴趣:" + interests +"<br/>");
        out.print("</body>");
        out.print("</html>");
    }
}
