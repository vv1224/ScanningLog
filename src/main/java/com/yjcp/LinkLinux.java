package com.yjcp;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;

public class LinkLinux {

    /**
     * 测试Socket连接
     */
  /*   @Test
    public void testLink(){

       String ip = "10.16.10.17";
        int port=6617;
        InputStream in;
        OutputStream out;
        Socket sock = null;
        try {
            sock = new Socket(ip,port);
            sock.setSoTimeout(60*1000);//设置超时
            in = sock.getInputStream();
            out = sock.getOutputStream();
            System.out.print("连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 测试Jsch链接linux服务器
     */
    @Test
    public void testJsch(){

        JSch jsch = new JSch(); // 创建JSch对象
        String userName = "sunwei";// 用户名
        String password = "sunwei";// 密码
        String host = "192.168.201.13";// 服务器地址
        int port = 6613;// 端口号
        String cmd = "cd /data";// 要运行的命令
        Session session = null; // 根据用户名，主机ip，端口获取一个Session对象
        try {
            session = jsch.getSession(userName, host, port);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        session.setPassword(password); // 设置密码
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        int timeout = 60000000;
        try {
            session.setTimeout(timeout); // 设置timeout时间
        } catch (JSchException e) {
            System.out.println("连接超时");
            e.printStackTrace();
        }
        try {
            session.connect(); // 通过Session建立链接
        } catch (JSchException e) {
            System.out.println("创建连接失败");
            e.printStackTrace();
        }

    }

}
