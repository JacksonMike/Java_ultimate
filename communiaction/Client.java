package com.codingke.communiaction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 客户端
 * @comp: http://www.codingke.com
 * @author: 千锋-威哥
 * @威哥答疑微信群: 1872783708
 * @date: 2019/8/28
 */
public class Client {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ExecutorService es = Executors.newSingleThreadExecutor();//单线程
        try {

            Socket socket = new Socket("localhost", 9988);
            System.out.println("服务器连接成功");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


            //-------------------模拟用户登录开始-------------------------
            //向服务器发送登录信息
            System.out.println("请输入昵称：");
            String name = input.nextLine();
            //封装数据包
            Message m = new Message(name,null,MessageType.TYPE_LOGIN,null);
            out.writeObject(m);//向服务器发消息
            //从服务器读取消息
            m = (Message) in.readObject();
            System.out.println(m.getInfo()+m.getFrom());
            //-------------------模拟用户登录结束-------------------------

            //启动读取消息的线程
            es.execute(new ReadInfoThread(in));

            //通过主线程来实现发送消息
            boolean flag = true;
            while (flag){
                m = new Message();
                System.out.println("To：");
                m.setTo(input.nextLine());
                System.out.println("Info：");
                m.setInfo(input.nextLine());
                m.setFrom(name);
                m.setType(MessageType.TYPE_SEND);
                out.writeObject(m);
            }

        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

//客户端读取消息的线程
class ReadInfoThread implements Runnable{
    private ObjectInputStream in;
    private boolean flag = true;
    public ReadInfoThread(ObjectInputStream in) {
        this.in = in;
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag){
            try {
                Message message = (Message) in.readObject();
                System.out.println("["+message.getFrom()+"]对我说："+message.getInfo());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(in!=null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
