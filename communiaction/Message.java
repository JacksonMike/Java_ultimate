package com.codingke.communiaction;

import java.io.Serializable;

/**
 * @description: 自定义的数据包类
 * @comp: http://www.codingke.com
 * @author: 千锋-威哥
 * @威哥答疑微信群: 1872783708
 * @date: 2019/8/28
 */
public class Message implements Serializable{
    private String from;//发送者
    private String to;//接收者
    private int type; //消息类型
    private String info;//消息内容

    public Message() {
    }

    public Message(String from, String to, int type, String info) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", type=" + type +
                ", info='" + info + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
