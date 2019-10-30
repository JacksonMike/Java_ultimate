package com.day6;

/**
 * @author Jackson
 * @date 2019-10-30 13:58
 */

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Food food = new Food();
        Consumer consumer = new Consumer(food);
        Producer producer = new Producer(food);
        Thread thread = new Thread(producer);
        Thread thread1 = new Thread(consumer);
        thread.start();
        thread1.start();
    }
}
class Consumer implements Runnable{
    private Food food;
    public Consumer(Food food){
        this.food = food;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            food.get();
        }
    }
}
class Producer implements Runnable{
    private Food food;
    public Producer(Food food){
        this.food = food;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                food.set("佛跳墙", "闽菜");
            }else {
                food.set("白切鸡", "粤菜");
            }
        }
    }
}
class Food {
    // 菜品名称
    private String name;
    // 所属菜系
    private String type;
    // 是否可以生产
    private boolean flag = true;
    // 炒菜
    public void set(String name, String type)  {
        // 对象上锁
        synchronized (this){
            if (!flag){
                try {
                    // 让出CPU 释放对象锁
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 表示正在生产
            this.name = name;
            // 模拟生产过程
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.type = type;
            flag = false;
            // 唤醒其他线程
            this.notify();
        }
    }
    // 吃菜
    public void get(){
        synchronized (this){
            // 表示可以生产 不能消费
            if (flag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 模拟消费过程
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.name + "->" + this.type);
            flag = true;
            this.notify();
        }
    }
    public Food() {
    }

    public Food(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
