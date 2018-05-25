package javax.servlet;

import java.io.PrintWriter;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/5/25
 * Time: 15:56
 * Description:封装响应数据的接口
 */
public interface ServletResponse {
    public abstract void setWriter(PrintWriter out);
    public abstract PrintWriter getWriter();
}
