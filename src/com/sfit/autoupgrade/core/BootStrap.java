package com.sfit.autoupgrade.core;

import com.sfit.autoupgrade.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/4/9
 * Time: 21:20
 * Description:AutoUpgrade自动升级主入口
 */
public class BootStrap {
    /**
     * 主程序
     * @param args
     */
    public static void main(String[] args) {
        start();
    }

    /**
     * 主程序入口
     */
    private static void start() {
        //创建服务器端套接字，并绑定端口
        try {
            Logger.log("AutoUpgrade Start!");
            ServerSocket serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
