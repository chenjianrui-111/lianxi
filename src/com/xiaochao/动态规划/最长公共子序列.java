package com.xiaochao.动态规划;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 */
public class 最长公共子序列 {
    public int longestCommonSubsequence(String text1, String text2) {
        return dp(text1,0,text2,0);
    }
    int dp(String text1,int i,String text2,int j){
        //base case
        if (i == text1.length() || j == text2.length()){
            return 0;
        }
        if (text1.charAt(i) == text2.charAt(j)){
            //text1[i] 和 text2[j] 必然在 lcs中
            //加上 text1[i+1..]和 text2[j+1..]中的 lcs长度，就是答案
            return dp(text1, i + 1, text2, j + 1) + 1;
        }else {
            // s1[i] 和 s2[j] 中至少有一个字符不在 lcs 中，
            // 穷举三种情况的结果，取其中的最大结果
            return max(
                    // 情况一、s1[i] 不在 lcs 中
                    dp(text1, i + 1, text2, j),
                    // 情况二、s2[j] 不在 lcs 中
                    dp(text1, i, text2, j + 1),
                    // 情况三、都不在 lcs 中
                    dp(text1, i + 1, text2, j + 1)
            );
        }
    }

    private int max(int dp, int dp1, int dp2) {
        return Math.max(dp,Math.max(dp1,dp2));
    }
}
class Solution1{
    int[][] memo;
    public int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(),n = s2.length();
        memo = new int[m][n];
        return dp(s1,0,s2,0);
    }
    int dp(String s1,int i,String s2,int j){
        if (i == s1.length() || j == s2.length()){
            return 0;
        }
        if (memo[i][j] != -1){
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)){
            memo[i][j] = 1 + dp(s1, i+1, s2, j+1);
        }else {
            memo[i][j] = Math.max(
                    dp(s1, i+1, s2, j),
                    dp(s1, i, s2, j+1)
            );
        }
        return memo[i][j];
    }
}
class Solution11{
    public int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(),n =s2.length();
        int[][] dp = new int[m+1][n+1];
        // 定义：s1[0..i-1] 和 s2[0..j-1] 的 lcs 长度为 dp[i][j]
        // 目标：s1[0..m-1] 和 s2[0..n-1] 的 lcs 长度，即 dp[m][n]
        // base case: dp[0][..] = dp[..][0] = 0
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                // s1[i-1] 和 s2[j-1] 至少有一个不在 lcs 中
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                    // s1[i-1] 和 s2[j-1] 必然在 lcs 中
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else {
                    // s1[i-1] 和 s2[j-1] 至少有一个不在 lcs 中
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }
}
