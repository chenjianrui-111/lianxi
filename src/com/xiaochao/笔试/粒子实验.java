package com.xiaochao.笔试;
import java.util.Scanner;

/**
 * 一些科学家在研究X粒子的特性，通常情况下，X粒子在经过加速装置后拥有的速度均为V，但是加热后，某些粒子的特性发生了变化，在相同情况下经过加速装置后拥有的速度变得大于V了（变化后的速度不一定相同），于是科学家们决定研究这些特别的粒子。
 * 科学家们对n个粒子做了特殊处理，为其从1到n分别编号，为了找出是哪些粒子的特性发生了变化（导致速度变化），他们准备让这些粒子依次通过一段相同长度的距离，速度越高的粒子通过这段距离所需的时间越短，由于技术问题，只能检测到粒子发射顺序和到达终点的顺序（没有两个粒子同时被发射或同时到达），请你通过这些数据计算出至少有多少个粒子特性发生了变化（即速度大于通常情况）。
 * 输入描述
 * 第一行一个正整数n，表示粒子数量。
 * 接下来一行包含n个正整数s1, s2,...... sn(1≤si≤n)，为按发射顺序给出的粒子编号，s1-sn为1-n的一个排列。
 * 接下来一行包含n个正整数p1,P2 ,...... pn(1≤pi≤n)，为按到达终点顺序给出的粒子编号，p1-pn为1-n的一个排列。
 * 输出描述
 * 输出至少有多少个粒子特性发生了变化（即速度大于通常情况）
 * 样例输入
 * 5
 * 5 4 3 2 1
 * 1 5 3 4 2
 * 样例输出
 * 2
 */
public class 粒子实验 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] arr2 = new int[n];
        for (int i = 0;i < n;i++){
            arr[i] = sc.nextInt();
        }
        for (int j = 0; j < n ; j++) {
            arr2[j] = sc.nextInt();
        }
        StringBuilder sb =new StringBuilder();
        StringBuilder sb2 =new StringBuilder();
        for (int i = 0; i <arr.length -1 ; i++) {
            sb.append(arr[i]);
        }
        for (int i = 0; i <arr2.length ; i++) {
            sb2.append(arr2[i]);
        }
        String text1 = sb.toString();
        String text2 = sb2.toString();
        int k = longestCommonSubsequence(text1,text2);
        System.out.println(n - k);
    }
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
class Solution2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n ; i++) {
            start[i] = sc.nextInt();
        }
        for (int i = 0; i < n ; i++) {
            end[i] = sc.nextInt();
        }
        int count = 0;
        boolean[] flag = new boolean[n+1];
        for (int i = 0,j = 0; i < n && j < n ; ) {
            if (!flag[start[i]]){
                if (start[i] == end[j]){
                    i++;
                }else {
                    count++;
                }
                flag[end[j]] = true;
                j++;
            }else {
                i++;
            }
        }
        System.out.println(count);
    }
}
