package org.sfit.oa.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/4/24
 * Time: 15:02
 * Description:处理登录业务的类
 */
public class LoginServlet implements Servlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) {
        PrintWriter out = response.getWriter();
        System.out.println("正在验证身份，请稍后...");
        out.print("<html>");
        out.print("<head>");
        out.print("<title>验证身份</title>");
        out.print("<meta charset='utf-8'/>");
        out.print("</head>");
        out.print("<body>");
        out.print("<center><font size='35' color='blue'>正在验证身份，请稍后！</font></center>");
        out.print("</body>");
        out.print("</html>");
    }
}
