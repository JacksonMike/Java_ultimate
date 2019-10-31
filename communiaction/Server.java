package com.codingke.communiaction;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 服务器
 * @comp: http://www.codingke.com
 * @author: 千锋-威哥
 * @威哥答疑微信群: 1872783708
 * @date: 2019/8/28
 */
public class Server {

    public static void main(String[] args) {
        //用来保存与客户端通信的用户线程（UserThread）
        Vector<UserThread> vector = new Vector<>();
        //定义一个线程池，固定大小是5
        ExecutorService es = Executors.newFixedThreadPool(5);
        //TCP协议
        try {
            ServerSocket server = new ServerSocket(9988);
            System.out.println("服务器已启动，正在等待客户端连接...");
            while (true){
                Socket socket = server.accept();//此方法表示等待客户端连接，此方法会阻塞
                UserThread user = new UserThread(socket,vector);
                //把runnable接口作为一个任务，传入线程池中，那么线程池会启动一个线程来执行这个任务
                es.execute(user);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

//处理客户端消息的线程
class UserThread implements Runnable{
    private String name;//代表是的客户端唯一的昵称
    private Socket socket; //代表此线程任务服务的客户端socket
    private Vector<UserThread> vector;
    private ObjectInputStream in; //对象输入流（IO 课程对象流），读
    private ObjectOutputStream out;//对象输出流，写
    private boolean flag = true;//循环的标记

    public UserThread(Socket socket, Vector<UserThread> vector) {
        this.socket = socket;
        this.vector = vector;
        this.vector.add(this);
    }

    @Override
    public void run() {
        //1、谁连接了
        System.out.println("客户端"+socket.getInetAddress().getHostAddress()+"已连接");//提示 IP 连接
        try {
            //2、初始化输入输出流
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            //3、循环读取消息
            while (flag) {
                Message m = (Message) in.readObject();
                //4、判断消息的类型
                switch (m.getType()) {
                    //5、根据不同的消息类型做相应的处理
                    case MessageType.TYPE_SEND:
                        String to = m.getTo(); //接收者是谁
                        int size = vector.size();
                        UserThread ut;
                        for (int i = 0; i < size; i++) {
                            ut = vector.get(i);
                            if (to.equals(ut.name) && ut != this) {
                                //找到了客户端所对应的中转线程，通过中转线程把消息发送给客户端
                                ut.out.writeObject(m);
                                break;
                            }
                        }
                        break;
                    case MessageType.TYPE_LOGIN:
                        name = m.getFrom();
                        m.setInfo("欢迎你：");
                        out.writeObject(m);
                        break;
                }

            }
            in.close();
            out.close();
        }catch (EOFException e){
            flag = false;
            System.out.println("客户端"+socket.getInetAddress().getHostAddress()+"已断开");//提示
        }catch (IOException|ClassNotFoundException e){
            e.printStackTrace();
            flag = false;
        }

    }
}