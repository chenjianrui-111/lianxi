package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 移位游戏
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 一天你正在玩一个游戏，游戏中给定一个数a，你需要通过一些简单的移位操作来将其变成b，在每次操作中，你可以将当前的数x变成以下六个数中的一个：
 * x * 2，x * 4，x * 8，x / 2 (如果x被2整除)，x / 4 (如果x被4整除)，x / 8 (如果x被8整除)
 * 例如，如果当前的数x = 12，你可以将他变成24、48、96、6、3，你不能将其变成x / 8，因为12不能被8整除。
 * 现在请问将给定的初始值a通过上述操作变成目标值b需要的最少的操作次数。
 * 输入描述
 * 第一行包含一个正整数t (1≤ t ≤100) ，表示数据组数。
 * 接下来n行每行包括两个空格隔开的正整数a和b，分别表示初始值和目标值。
 * 输出描述
 * 输出n行，每行一个数表示通过上述操作将初始值a变成目标值b需要的最少的操作次数，如果最终无法得到b，则输出-1 。
 * 样例输入
 * 4
 * 3 6
 * 16 2
 * 12 4
 * 1024 1
 * 样例输出
 * 1
 * 1
 * -1
 * 4
 * 提示
 * 对于100%的数据，1≤ a,b ≤1018
 */
public class 移位游戏 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long max = Math.max(a, b);
            long min = Math.min(a, b);
            if (max % min != 0) {
                System.out.println(-1);
            } else {
                long cur = max / min;
                int res = 0;
                while (cur > 1 && cur % 8 == 0) {
                    cur /= 8;
                    res++;
                }
                while (cur > 1 && cur % 4 == 0) {
                    cur /= 4;
                    res++;
                }
                while (cur > 1 && cur % 2 == 0) {
                    cur /= 2;
                    res++;
                }
                if (cur == 1) {
                    System.out.println(res);
                } else {
                    System.out.println(-1);
                }
            }
        }
    }
}
