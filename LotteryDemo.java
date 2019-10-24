package com.day1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Jackson
 * @date 2019-10-24 19:44
 * 模拟双色球综合案例
 * 1、准备相关的变量N个
 * 2、用户选择是机选还是手选号码
 * 3、接收用户选号（6红1蓝） 红1-33 蓝1-16
 * 4、系统生成号码（6红1蓝）
 * 5、验证系统号码和用户号码，记录选中的号
 * 6、验证是否中奖
 * 7、把号码排序
 * 8、公布结果
 */

public class LotteryDemo {
    public static void main(String[] args) {
        // 用户产生的红球号码
        int[] userRedBall = new int[6];
        // 系统产生的红球号码
        int[] systemRedBall = new int[6];
        // 用户产生的蓝球号码
        int userBlueBall = 0;
        // 系统产生的蓝球号码
        int systemBlueBall = 0;
        // 总共的红球号码集合 1-33
        int[] allRedBall = new int[33];
        // 用户选中的红球数量
        int redCount = 0;
        // 用户选中的蓝球数量
        int blueCount = 0;
        // 添加红球号码集合
        for (int i = 0; i < allRedBall.length; i++) {
            allRedBall[i] = i + 1;
        }


        // 接受用户选择
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入是机器选择还是手动选择号码 (1, 机器选择),(2, 手动选择):");
        // 随机数对象
        Random random = new Random();
        // 设置标记
        boolean flag = true;
        while (flag){
            // 记录用户的选择序号
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // 蓝球号码
                    userBlueBall = random.nextInt(16) + 1;
                    // 红球号码
                    method(allRedBall, userRedBall);
                    flag = false;
                    break;
                case 2:
                    // 蓝球号码
                    System.out.println("请输入你选择的蓝球号码1-16");
                    userBlueBall = random.nextInt();
                    // 红球号码
                    System.out.println("请输入你选择的6个红球号码1-33");
                    ManualChoose(userRedBall);
                    break;
                default:
                    System.out.println("号码选择有误,请重新输入");
                    break;
            }
        }

        // 系统产生的蓝球号码
        systemBlueBall = random.nextInt(16) + 1;
        // 系统产生的红球号码
        method(allRedBall, systemRedBall);


        // 验证系统号码和用户选择的号码
        if (userBlueBall == systemBlueBall){
            blueCount += 1;
        }

        for (int i = 0; i < userRedBall.length; i++) {
            for(int j = 0; j < systemRedBall.length - redCount; j++){
                if (userRedBall[i] == systemRedBall[j]){
                    int temp = systemRedBall[j];
                    systemRedBall[j] = systemRedBall[systemRedBall.length - 1 - redCount];
                    systemRedBall[systemRedBall.length - 1 - redCount] = temp;
                    redCount += 1;
                    break;
                }
            }
        }


        if(blueCount==0 && redCount <=3){
            System.out.println("革命尚未成功，同志还需努力！再下一注");
        }else if(blueCount==1 && redCount<3){
            System.out.println("恭喜你！中了六等奖，5块钱带回家");
        }else if((blueCount==1 && redCount==3) || (blueCount==0 && redCount==4)){
            System.out.println("恭喜你！中了五等奖，10块钱带回家");
        }else if((blueCount==1 && redCount==4) || (blueCount==0 && redCount==5)){
            System.out.println("恭喜你！中了四等奖，200块钱带回家");
        }else if(blueCount==1 && redCount==5){
            System.out.println("恭喜你！中了三等奖，3000块钱带回家");
        }else if(blueCount==0 && redCount==6){
            System.out.println("恭喜你！中了二等奖，可以买辆车取媳妇啦");
        }else if(blueCount==1 && redCount==6){
            System.out.println("恭喜你！中了一等奖，500W，想干嘛就干嘛");
        }else{
            System.out.println("风里雨里，工地等你！");
        }

        //公布系统号码
        System.out.println("本期中奖红球号码为：");
        Arrays.sort(systemRedBall);
        System.out.println(Arrays.toString(systemRedBall));
        System.out.println("本期中奖蓝球号码为："+systemBlueBall);

        //公布用户选码
        System.out.println("您选择的红球号码为：");
        Arrays.sort(userRedBall);
        System.out.println(Arrays.toString(userRedBall));
        System.out.println("您选择的蓝球号码为："+userBlueBall);
    }

    // 手动选择
    public static void ManualChoose(int arr[]) {

        for (int i = 0; i < 6; i++) {
            Scanner sc1 = new Scanner(System.in);
            arr[i] = sc1.nextInt();
        }
    }

    // 随机生成多个不重复算法
    public static void method(int a[], int b[]) {
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            Random random = new Random();
            j = random.nextInt(a.length - i);
            b[i] = a[j];
            // 讲a[j]换到最后
            int temp = a[j];
            a[j] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }
}
