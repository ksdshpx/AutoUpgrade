package javax.servlet;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/4/27
 * Time: 16:06
 * Description:由SUN公司制定的Servlet接口，该接口由web服务器开发人员调用，由webapp开发人员实现
 */
public interface Servlet {
    //处理业务的核心方法
    public abstract void service();
}
