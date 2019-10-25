package com.day2;

import java.util.Arrays;

/**
 * @author Jackson
 * @date 2019-10-25 13:51
 */

public class DynamicArray {
    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList(3);
        myArrayList.addElement(new Dog(1, "Ann", 3));
        myArrayList.addElement(new Dog(2, "Bob", 4));
        myArrayList.addElement(new Dog(3, "Cook", 5));
        myArrayList.addElement(new Dog(4, "David", 6));
        myArrayList.addElement(new Dog(5, "Frank", 9));
        myArrayList.printAll();
        myArrayList.removeElement(4);
        myArrayList.printAll();
        myArrayList.updateElement(5, "Jack", 100);
        myArrayList.printAll();
        Dog d = myArrayList.searchElement(5);
        System.out.println(d);
    }
}

class MyArrayList {
    // 自定义需要管理的动态数组
    private Dog[] dogs = null;
    // 数组中的元素个数
    private int count = 0;

    // 构造方法
    public MyArrayList(int size) {
        if (size > 0) {
            dogs = new Dog[size];
        } else {
            // 默认五个
            dogs = new Dog[5];
        }
    }

    // 返回数组长度
    public int getLength() {
        return dogs.length;
    }

    // 返回数组中的元素个数
    public int getCount() {
        return count;
    }

    // 打印数组中的所有元素
    public void printAll() {
        for (int i = 0; i < count; i++) {
            dogs[i].method();
        }
    }

    // 增加数组元素
    public void addElement(Dog dog) {
        // 判断数组是否已满
        if (count >= dogs.length) {
            // 讲原来的数组长度扩大一倍
            int newLength = dogs.length * 2;
            // 复制 根据原来的数组 创建一个新数组
            dogs = Arrays.copyOf(dogs, newLength);
        }
        // 添加数组元素
        dogs[count] = dog;
        count++;
    }

    // 删除数组元素 根据id值来删除
    public void removeElement(int id) {
        // 获取要删除的对象
        for (int i = 0; i < count; i++) {
            if (dogs[i].getId() == id) {
                // (覆盖)将该元素后面的元素整体向后移动
                for (int j = i; j < count; j++) {
                    dogs[j] = dogs[j + 1];
                    // 将数组最后一个元素置空
                    dogs[count - 1] = null;
                    count--;
                    break;
                }
            }
        }
    }
    // 更新数组元素 根据id来更新
    public void updateElement(int id, String name, int age){
        for (int i = 0; i < count; i++) {
            if (dogs[i].getId() == id){
                dogs[i].setName(name);
                dogs[i].setAge(age);
            }
        }
    }
    // 查询数组元素 根据id来查询
    public Dog searchElement(int id){
        for (int i = 0; i < count; i++) {
            if (dogs[i].getId() == id){
                return dogs[i];
            }
        }
        return null;
    }
}
