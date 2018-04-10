package com.sfit.autoupgrade.core;

import com.sfit.autoupgrade.util.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
     *
     * @param args
     */
    public static void main(String[] args) {
        start();
    }

    /**
     * 主程序入口
     */
    private static void start() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader bufferedReader = null;
        try {
            Logger.log("AutoUpgrade Start!");
            //获取系统启动开始时间
            long startTime = System.currentTimeMillis();
            //获取系统端口号
            int port = ServerParser.getPort();
            Logger.log("AutoUpgrade-Port:" + port);
            //创建服务器端套接字，并绑定端口
            serverSocket = new ServerSocket(port);
            //获取系统启动结束时间
            long endTime = System.currentTimeMillis();
            Logger.log("AutoUpgrade Started:hosts " + (endTime - startTime) + " ms");
            while (true) {
                //开始监听网络，此时程序处于等待状态，等待客户端升级请求
                clientSocket = serverSocket.accept();
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(clientSocket!=null){
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
