package com.day3;

/**
 * @author Jackson
 * @date 2019-10-26 20:19
 */

public class LinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        // 增加节点
        myLinkedList.add(8);
        myLinkedList.add(7);
        myLinkedList.add(2);
        myLinkedList.add(6);
        myLinkedList.add(5);
        myLinkedList.print();
        System.out.println("-----------------------");
        // 删除节点
        myLinkedList.delete(6);
        myLinkedList.print();
        System.out.println("-----------------------");
        // 更新节点
        myLinkedList.update(7, 9);
        myLinkedList.print();
        System.out.println("-----------------------");
        // 查询节点
        System.out.println(myLinkedList.search(80));
        System.out.println("-----------------------");
        // 前插节点
        System.out.println(myLinkedList.insertFront(3, 99));
        myLinkedList.print();
    }
}

// 定义一个链表类提供给外部使用
class MyLinkedList{
    // 定义根节点
    private Node root;
    // 定义当前节点下表
    private int currentIndex = 0;
    // 添加节点
    public void add(int data){
        if (root == null){
            root = new Node(data);
        }else {
            root.addTailNode(data);
        }
    }
    // 删除节点
    public void delete(int data){
        // 如果头节点为空 则直接结束
        if (root == null) return;
        // 如果头节点有值 隔空相连
        if (root.data == data){
            root = root.next;
        }else {
            // 递归操作
            root.deleteNode(data);
        }
    }
    // 更新节点
    public boolean update(int oldData, int newData){
        if (root != null){
            if (root.getData() == oldData){
                root.setData(newData);
                return true;
            }else {
                root.updateNode(oldData, newData);
            }
        }
        return false;
    }
    // 查询节点
    public boolean search(int data){
        if (root != null){
            if (root.getData() == data){
                return true;
            }else {
                root.searchNode(data);
            }
        }
        return false;
    }
    // 打印全部节点
    public void print(){
        if (root != null){
            // 打印根节点数据
            System.out.print(root.getData()+"->");
            // 打印根节点下的数据
            root.printNode();
            // 换行
            System.out.println();
        }
    }
    // 前插节点
    public boolean insertFront(int index, int data){
        if (index < 0)return false;
        currentIndex = 0;
        // 表示在根节点之前插入
        if (currentIndex == index){
            // 创建新节点
            Node node = new Node(data);
            node.next = root.next;
            root.next = node;
            return true;
        }else {
            return root.next.insetFrontNode(index, data);
        }
    }
    // 使用内部类来定义链表的节点对象
    private class Node{
        // 节点存储的数据
        private int data;
        // 把当前自己的类型当作属性
        private Node next, head;
        // 有参的构造方法
        public Node(int data){
            this.data = data;
        }
        // 设置数据
        public void setData(int data){
            this.data = data;
        }
        // 获取数据
        public int getData(){
            return data;
        }
        // 添加节点(尾添加)
        public void addTailNode(int data){
            // 后面为空直接添加
            if (this.next == null){
                this.next = new Node(data);
            }else {
                // 递归操作
                this.next.addTailNode(data);
            }
        }
        // 删除节点(删除该节点后面的一个节点)
        public void deleteNode(int data){
            // 如果该节点后面不为空
            if (this.next != null){
                // 如果数据域相等
                if (this.next.data == data){
                    // 隔空相连
                    this.next = this.next.next;
                }else {
                    // 递归操作
                    this.next.deleteNode(data);
                }
            }
        }
        // 更新节点(更新该节点后面的一个节点数据)
        public boolean updateNode(int oldData, int newData){
            if (this.next != null){
                if (this.next.data == oldData){
                    this.next.data = newData;
                    return true;
                }else {
                    this.next.updateNode(oldData, newData);
                }
            }
            return false;
        }
        // 查询节点(查询该节点后面的一个节点的数据)
        public boolean searchNode(int data){
            if (this.next != null){
                if (this.next.data == data){
                    return true;
                }else {
                    this.next.searchNode(data);
                }
            }
            return false;
        }
        // 输出所有节点
        public void printNode(){
            if (this.next != null){
                // 输出当前节点的下一个节点的数据
                System.out.print(this.next.data+"->");
                // 递归操作
                this.next.printNode();
            }
        }
        // 前插节点
        public boolean insetFrontNode(int index, int data){
            if (this.next != null){
                // 进入下一个节点
                currentIndex += 1;
                if (currentIndex == index){
                    // 新建一个节点
                    Node node = new Node(data);
                    // 新节点的下一个节点为原来节点的下一个节点
                    node.next = this.next;
                    // 原节点的下一个节点为新节点
                    this.next = node;
                    return true;
                }else {
                    return this.next.insetFrontNode(index, data);
                }
            }else {
                // 如果为空则到达链表末尾 直接添加元素
                this.addTailNode(data);
                return true;
            }
        }
    }
}
