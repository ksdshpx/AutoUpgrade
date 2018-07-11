package com.sfit.autoupgrade.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/4/27
 * Time: 14:13
 * Description:解析服务器中的web.xml
 */
public class WebParser {
    public static Map<String, Map<String, String>> getServletMaps() {
        return servletMaps;
    }

    public static void setServletMaps(Map<String, Map<String, String>> servletMaps) {
        WebParser.servletMaps = servletMaps;
    }

    private static Map<String,Map<String,String>> servletMaps = new HashMap<>();

    /**
     * 解析所有应用的web.xml
     * @param webAppNames
     */
    public static void parse(String[] webAppNames) throws DocumentException {
        for (String webAppName : webAppNames) {
            Map<String, String> servletMap = parse(webAppName);
            servletMaps.put(webAppName,servletMap);
        }
    }
    /**
     * 解析单个应用的web.xml配置文件
     * @param webAppName 应用名称
     * @return servletMap<String,String>
     */
    private static Map<String,String> parse(String webAppName) throws DocumentException {
        //获取web.xml的路径
        String webPath = webAppName + "/WEB-INF/web.xml";
        //创建解析器
        SAXReader saxReader = new SAXReader();
        //通过解析器的read方法将配置文件读取到内存中，生成一个Document对象树
        Document document = saxReader.read(new File(webPath));
        //获取servlet节点元素：web-app -> servlet
        List<Element> servletNodes = document.selectNodes("/web-app/servlet");
        //创建servletInfoMap集合，将servlet-name和servlet-class分别作为key和value放入servletInfoMap集合中
        Map<String,String> servletInfoMap = new HashMap<String,String>();
        //遍历servletNodes
         for (Element servletNode : servletNodes) {
            //获取servlet-name节点对象
            Element servletNameElt = (Element) servletNode.selectSingleNode("servlet-name");
            //获取servletNameElt对象对应的值
            String servletName = servletNameElt.getStringValue();
            //获取servlet-class节点对象
            Element servletClassElt = (Element) servletNode.selectSingleNode("servlet-class");
            //获取servletNameElt对象对应的值
            String servletClass = servletClassElt.getStringValue();
            //将servletName和servletClass分别作为key和value放入servletInfoMap对象中
            servletInfoMap.put(servletName,servletClass);
        }

        //获取servletMapping节点元素：web-app -> servlet-mapping
        List<Element> servletMappingNodes = document.selectNodes("/web-app/servlet-mapping");
        //创建servletMappingInfoMap集合，将servlet-name和url-pattern分别作为key和value放入servletMappingInfoMap集合中
        Map<String,String> servletMappingInfoMap = new HashMap<String,String>();
        //遍历servletMappingNodes
        for (Element servletMappingNode : servletMappingNodes) {
            //获取servlet-name节点对象
            Element servletNameElt = (Element) servletMappingNode.selectSingleNode("servlet-name");
            //获取servletNameElt对象对应的值
            String servletName = servletNameElt.getStringValue();
            //获取url-pattern节点对象
            Element urlPatternElt = (Element) servletMappingNode.selectSingleNode("url-pattern");
            //获取urlPatternElt对象对应的值
            String urlPattern = urlPatternElt.getStringValue();
            //将servletName和urlPattern分别作为key和value放入servletMappingInfoMap对象中
            servletMappingInfoMap.put(servletName,urlPattern);
        }
        //获取servletInfoMap或servletMappingInfoMap任何一个key值的集合
        Set<String> servletNames = servletInfoMap.keySet();
        //创建一个servletMap集合，将servletMappingInfoMap的value和servletInfoMap的value分别作为key和value存放到该集合中
        Map<String,String> servletMap = new HashMap<>();
        //遍历servletNames
        for (String servletName : servletNames) {
            //获取servletMappingInfoMap中的value:url-pattern
            String urlPattern = servletMappingInfoMap.get(servletName);
            //获取servletInfoMap中的value:servlet-class
            String servletClassName = servletInfoMap.get(servletName);
            //将urlPattern和servletClassName分别作为key和value存放到servletMap中
            servletMap.put(urlPattern,servletClassName);
        }
        return servletMap;
    }
}
