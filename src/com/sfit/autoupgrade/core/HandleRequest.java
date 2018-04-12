package com.sfit.autoupgrade.core;

import com.sfit.autoupgrade.util.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/4/10
 * Time: 14:49
 * Description:处理客户端请求
 */
public class HandleRequest implements Runnable {
    private Socket clientSocket;

    public HandleRequest(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        //处理客户端请求
        BufferedReader bufferedReader = null;
        Logger.log("AutoUpgrade thread:" + Thread.currentThread().getName());
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //打印客户端消息
            /*String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }*/
            //获取请求协议的请求行
            String requestLine = bufferedReader.readLine();//GET /oa/index.jsp HTTP/1.1
            //获取URI
            String requestURI = requestLine.split("\\s")[1];
            System.out.println("requestURI:" + requestURI);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
