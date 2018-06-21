package javax.servlet;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/6/21
 * Time: 16:13
 * Description:封装请求数据的接口
 */
public interface ServletRequest {
    /**
     * 获取单个参数的值
     * @param key
     * @return
     */
    public abstract String getParameterValue(String key);

    /**
     * 获取多个参数的值
     * @param key
     * @return
     */
    public abstract String[] getParameterValues(String key);
}
