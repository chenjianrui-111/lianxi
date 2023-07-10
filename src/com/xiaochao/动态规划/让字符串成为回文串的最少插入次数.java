package com.xiaochao.动态规划;

public class 让字符串成为回文串的最少插入次数 {
    int minInsertions(String s) {
        int n = s.length();
        // dp[i][j] 表示把字符串 s[i..j] 变成回文串的最少插入次数
        // dp 数组全部初始化为 0
        int[][] dp =new int[n][n];
        for (int i = n - 1; i >=0 ; i--) {
            for (int j = i + 1; j < n ; j++) {
                //状态转移方程
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    // 把 s[i+1..j] 和 s[i..j-1] 变成回文串，选插入次数较少的
                    // 然后还要再插入一个 s[i] 或 s[j]，使 s[i..j] 配成回文串
                    dp[i][j] = Math.min(dp[i+1][j],dp[i][j-1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }
}
