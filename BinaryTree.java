package com.day4;

import jdk.nashorn.internal.ir.IfNode;

/**
 * @author Jackson
 * @date 2019-10-28 16:04
 */

public class BinaryTree {
    // 根节点
    private Node root;
    // 增加节点
    public void add(int data){
        if (root == null){
            root = new Node(data);
        }else {
            root.addNode(data);
        }
    }
    // 打印节点
    public void print(){
        if (root != null){
            root.printNode();
        }
    }
    private class Node{
        // 数据域
        private int data;
        // 左子树
        private Node left;
        // 右子树
        private Node right;
        // 构造方法
        public Node(int data){
            this.data = data;
        }
        // 增加节点
        public void addNode(int data){
            if (data < this.data){
                if (this.left == null){
                    this.left = new Node(data);
                }else {
                    this.left.addNode(data);
                }
            }else {
                if (this.right == null){
                    this.right = new Node(data);
                }else {
                    this.right.addNode(data);
                }
            }
        }
        // 遍历(中序遍历)
        public void printNode(){
            if (this.left != null){
                this.left.printNode();
            }
            System.out.print(this.data + "=>");
            if (this.right != null){
                this.right.printNode();
            }
        }
    }
}
