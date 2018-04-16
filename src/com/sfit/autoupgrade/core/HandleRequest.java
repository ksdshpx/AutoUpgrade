package com.sfit.autoupgrade.core;

import com.sfit.autoupgrade.util.Logger;

import java.io.*;
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
        PrintWriter out = null;
        Logger.log("AutoUpgrade thread:" + Thread.currentThread().getName());
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //获取响应流对象
            out = new PrintWriter(clientSocket.getOutputStream());
            //打印客户端消息
            /*String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }*/
            //获取请求协议的请求行
            String requestLine = bufferedReader.readLine();//GET /oa/index.jsp HTTP/1.1
            //获取URI
            String requestURI = requestLine.split("\\s")[1];
            //System.out.println("requestURI:" + requestURI);
            //判断用户请求的是否为一个静态页面
            if (requestURI != null) {
                if (requestURI.endsWith(".html") || requestURI.endsWith(".htm")) {
                    //响应静态页面
                    responseStaticPage(requestURI, out);
                }
            }
            //刷新流
            out.flush();
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

    /**
     * 处理静态页面
     *
     * @param requestURI 请求URI
     * @param out        响应流对象
     */
    private void responseStaticPage(String requestURI, PrintWriter out) {
        //requestURI:/oa/index.html
        //静态页面的路径:oa/index.html
        String htmlPath = requestURI.substring(1);
        BufferedReader br = null;
        try {
            //读取页面
            br = new BufferedReader(new FileReader(htmlPath));
            //拼接响应信息
            StringBuilder reponseInfo = new StringBuilder();
            reponseInfo.append("HTTP/1.1 200 OK\n");
            reponseInfo.append("Context-Type:text/html;charset=utf-8\n\n");
            String line = null;
            while ((line = br.readLine()) != null) {
                reponseInfo.append(line);
            }
            //响应
            out.print(reponseInfo.toString());
        } catch (FileNotFoundException e) {
            //找不到资源404
            StringBuilder responseInfo = new StringBuilder();
            responseInfo.append("HTTP/1.1 404 NotFound\n");
            responseInfo.append("Context-Type:text/html;charset=utf-8\n\n");
            responseInfo.append("<html>");
            responseInfo.append("<head>");
            responseInfo.append("<title>404错误页面</title>");
            responseInfo.append("<meta charset=\"utf-8\"");
            responseInfo.append("</head>");
            responseInfo.append("<body>");
            responseInfo.append("<center><font size=\"35px\" color=\"red\">404-NotFound</font></center>");
            responseInfo.append("</body>");
            responseInfo.append("</html>");
            out.print(responseInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
