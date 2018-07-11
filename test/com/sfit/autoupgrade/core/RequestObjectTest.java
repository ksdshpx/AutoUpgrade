package com.sfit.autoupgrade.core;

import org.junit.Test;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/6/21
 * Time: 15:33
 * Description:封装响应数据的对象的单元测试类
 */
public class RequestObjectTest {

    @Test
    public void testGetParameterValue() throws Exception {
        RequestObject request = new RequestObject("/oa/user/save?");
        System.out.println(request.getParameterValue("username"));
        RequestObject request1 = new RequestObject("/oa/user/save?username");
        System.out.println(request1.getParameterValue("username"));
        RequestObject request2 = new RequestObject("/oa/user/save?username=zhangsan");
        System.out.println(request2.getParameterValue("username"));
        RequestObject request3 = new RequestObject("/oa/user/save?username=zhangsan&gender=1");
        System.out.println(request3.getParameterValue("gender"));
        RequestObject request4 = new RequestObject("/oa/user/save?username=zhangsan&gender=1&interest=food&interest=music");
        System.out.println(request4.getParameterValue("interest"));
    }

    @Test
    public void testGetParameterValues() throws Exception {
        RequestObject request4 = new RequestObject("/oa/user/save?username=zhangsan&gender=1&interest=food&interest=music");
        String[] interests = request4.getParameterValues("interest");
        for (String interest : interests) {
            System.out.println(interest);
        }
    }

} 
