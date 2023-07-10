package com.cjr.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 牛牛现在有一个n个数组成的数列,牛牛现在想取一个连续的子序列,并且这个子序列还必须得满足:
 * 最多只改变一个数,就可以使得这个连续的子序列是一个严格上升的子序列,牛牛想知道这个连续子序列最长的长度是多少。
 * 输入描述:
 * 输入包括两行,第一行包括一个整数n(1 ≤ n ≤ 10^5),即数列的长度;
 * 第二行n个整数a_i, 表示数列中的每个数(1 ≤ a_i ≤ 10^9),以空格分割。
 * 输出描述:
 * 输出一个整数,表示最长的长度。
 * 示例1
 * 输入
 * 6
 * 7 2 3 1 5 6
 * 输出
 * 5
 */
public class shuLie {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] arr = new int[count];
        String[] strs = br.readLine().split(" ");
        for (int i = 0; i < count;i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }
        System.out.println(getMaxLengthGrow(count, arr));
    }
    public static int getMaxLengthGrow(int count, int[] arr){
        if(arr==null||count==0){
            return 0;
        }
        int[][] dp = new int[2][count];
        dp[0][0] = dp[1][0] = 1;
        int maxLength = 1;
        int[] temp = new int[count];
        for(int i=0;i<count;i++){
            temp[i]=arr[i];
        }
        for(int i=1;i<count;i++){
            if (arr[i] > arr[i - 1]) {
                dp[0][i] = dp[0][i - 1] + 1;
            } else {
                dp[0][i] = 1;
            }
            if (arr[i] > temp[i - 1]) {
                dp[1][i] = dp[1][i - 1] + 1;
            } else {
                dp[1][i] = dp[0][i-1]+1;
                temp[i]=arr[i-1]+1;
            }
            maxLength=Math.max(maxLength, dp[1][i]);
        }
        return maxLength;
    }
}
