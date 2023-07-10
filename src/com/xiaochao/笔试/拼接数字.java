package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 拼接数字
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 桌面上有n张卡片，每张卡片上都写有一个正整数，现在你可以从中选出三张卡片，将卡片上的三个数按任意顺序连接成一个新的数。
 * 例如，对于三个数字123、45、678，你可以将它们连成12345678、45123678、67845123、67812345或12367845等，可以证明67845123是能拼接出的最大的数。
 * 注意，对于卡片上的数字，你不能将其拆开。
 * 输入描述
 * 第一行是一个正整数n，表示有n张卡片。
 * 第二行是n个用空格隔开的正整数，其中第i个数a_i表示第i张卡片上写的数字。保证数字不含前导零。
 * 输出描述
 * 一行一个整数，表示能拼接出的最大的数。
 * 样例输入
 * 4
 * 123 45 678 23
 * 样例输出
 * 67845123
 * 提示
 * 100%的数据保证 ：1<=n<=100000, 1<=a_i<=20000000
 */
public class 拼接数字 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] nums = new String[n];
        for (int i = 0; i < n ; i++) {
            nums[i] = sc.next();
        }
        Arrays.sort(nums,(o1, o2) -> {
            if (o1.length() == o2.length()){
                return o1.compareTo(o2);
            }else
                return o1.length() - o2.length();
        });
        String[] temp = new String[]{nums[n - 1],nums[n - 2],nums[n - 3]};
        Arrays.sort(temp);
        StringBuilder ans = new StringBuilder();
        for (int i = temp.length - 1; i >= 0 ; i--) {
            ans.append(temp[i]);
        }
        System.out.println(ans);
    }
}
