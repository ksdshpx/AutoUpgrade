package com.sfit.autoupgrade.core;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/4/10
 * Time: 9:52
 * Description:解析server.xml配置文件
 */
public class ServerParser {
    /**
     * 获取服务器的端口号
     *
     * @return int port
     */
    public static int getPort() {
        //设置默认端口号
        int port = 9090;
        try {
            //创建解析器
            SAXReader saxReader = new SAXReader();
            //通过解析器的read方法将配置文件读取到内存中，生成一个Document对象树
            Document document = saxReader.read(System.getProperty("user.dir") + "/conf/server.xml");
            //获取connector节点元素的路径：server->service->connector
            //获取connector节点元素的xpath路径：/server/service/connector
            //获取connector节点元素的xpath路径：server//connector
            //获取connector节点元素的xpath路径：//connector
            Element connectorElt = (Element) document.selectSingleNode("//connector");
            port = Integer.parseInt(connectorElt.attributeValue("port"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return port;
    }
}
