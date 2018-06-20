package com.sfit.autoupgrade.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/6/20
 * Time: 15:34
 * Description:封装请求数据
 */
public class RequestObject {
    private Map<String, String[]> parameterMap = new HashMap<>();

    public RequestObject(String requestURI) {
        //判断requestURI是否包含？号，即是否有参数
        if (requestURI.contains("?")) {
            //将requestURI进行分割
            String[] uriAndData = requestURI.split("[?]");
            //再次判断requestURI是否包含参数
            if (uriAndData.length > 1) {
                //获取参数部分
                String data = uriAndData[1];
                //判断data中是否包含多个参数
                if (data.contains("&")) {//有多个参数
                    //通过&符号进行分割
                    String[] nameAndValues = data.split("&");
                    //遍历nameAndValues
                    for (String nameAndValue : nameAndValues) {
                        //通过=号进行分割
                        String[] nameAndValueAttr = nameAndValue.split("=");
                        //判断key值是否在parameterMap中存在
                        if (parameterMap.containsKey(nameAndValueAttr[0])) {
                            //1.如果存在，说明该参数为多选框
                            //将之前放的字符串数组取出来
                            String[] values = parameterMap.get(nameAndValueAttr[0]);
                            //定义一个新的数组，而新数组的长度永远比values数组长度大1
                            String[] newValues = new String[values.length + 1];
                            //数组复制
                            System.arraycopy(values,0,newValues,0,values.length);
                            //判断该参数是否有值
                            if(nameAndValueAttr.length > 1){
                                newValues[newValues.length -1] = nameAndValueAttr[1];
                            }else{
                                newValues[newValues.length -1] = "";
                            }
                            parameterMap.put(nameAndValueAttr[0], newValues);
                        } else {
                            //2.如果不存在，说明为普通的标签
                            //判断该参数是否有值
                            if (nameAndValueAttr.length > 1) {
                                parameterMap.put(nameAndValueAttr[0], new String[]{nameAndValueAttr[1]});
                            } else {
                                parameterMap.put(nameAndValueAttr[0], new String[]{""});
                            }
                        }
                    }
                } else {//单个参数
                    //data通过=号进行分割，判断参数是否有值
                    String[] nameAndValueAttr = data.split("=");
                    if (nameAndValueAttr.length > 1) {//有值
                        parameterMap.put(nameAndValueAttr[0], new String[]{nameAndValueAttr[1]});
                    } else {//无值
                        parameterMap.put(nameAndValueAttr[0], new String[]{""});
                    }
                }
            }
        }
    }
}
