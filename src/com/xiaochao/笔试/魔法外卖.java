package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 炸鸡店拥有一名会传送魔法的外卖派送员。
 * 该外卖派送员派送单子时，可以消耗时间t来正常派送单子（一次只能派送一个单子，不能多个同时派送），也可以使用魔法不耗费时间地隔空瞬间投送。
 * 现在炸鸡店在时刻0接收到了若干炸鸡订单，每个单子都有它的截止送达时间。外卖派送员需要保证送达时间小于等于这个截止时间。
 * 现在询问外卖员最少要使用几次魔法来保证没有外卖超时。
 * 输入描述
 * 第一行两个正整数n, t 以空格分开，表示当前接到了n个订单，外卖员在不使用魔法的情况下正常派送所需要消耗的时间t。
 * 第二行n个正整数，每个正整数表示一个订单的截止送达时间。
 * 1 <= n <= 1e5, 1 <= t <= 100, 订单的送达时间介于[1, 1e7]之间
 * 输出描述
 * 一行一个非负整数，表示外卖员最少需要使用的魔法次数。
 */
public class 魔法外卖 {
    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        String[] line = cin.nextLine().split(" ");
        int n = Integer.parseInt(line[0]), t = Integer.parseInt(line[1]);
        int[] delivery = Arrays.stream(cin.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int count = 0, time = 0;
        Arrays.sort(delivery);
        for (int i = 0; i < n; i++) {
            if (time + t <= delivery[i]){
                time += t;
            } else {
                count++;
            }
        }
        System.out.printf("%d", count);
    }
}
