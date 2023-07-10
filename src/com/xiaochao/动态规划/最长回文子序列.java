package com.xiaochao.动态规划;

/**
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 */
public class 最长回文子序列 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        //dp 数组初始化
        int[][] dp = new int[n][n];
        //base case
        for (int i = 0; i < n ; i++) {
            dp[i][i] = 1;
        }
        //反着遍历保证正确的状态转移
        for (int i = n - 1; i >= 0 ; i--) {
            for (int j = i + 1;j < n;j++){
                //状态转移方程
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
