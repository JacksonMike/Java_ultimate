package com.day5;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jackson
 * @date 2019-10-29 10:49
 */

public class HashMapDemo {
    public static void main(String[] args) {
        Map<Integer, String> m = new HashMap<>();
        m.put(1, "Jack");
        m.put(2, "Mike");
        System.out.println(m.get(1));

        // 按位异或 相同为0 不同为1
        // 0000 0001 0101 1001
        int a = 345;
        // 0000 0010 0011 0111
        int b = 567;
        // 0000 0011 0110 1110
        int c = a ^ b;
        System.out.println(c);

        // 无符号右移动16位,避免has值相近的对象存储在同一个数组, 减少碰撞
        // 假设d e的hashcode值,
        int d = 1234567;
        int e = 1234568;
        // d e的hash值
        // 1234581
        System.out.println(d ^ (d >>> 16));
        // 1234586
        System.out.println(e ^ (e >>> 16));

        // &与运算
        // int f = 1 & 0;

        // 此时哈希表数组中最大下标(假设为15)&hash值 则为对象存储的位置
        // 10
        System.out.println(15&1234581);
        // 10
        System.out.println(15&1234586);
    }
}
