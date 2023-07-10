package com.xiaochao.动态规划;

/**
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最⼩步数，每步可以删除任意⼀个字 符串中的⼀个字符。
 * 示例：输⼊："sea", "eat"
 * 输出：2 解释：第⼀步将 "sea" 变为 "ea"，第⼆步将 "eat" 变为 "ea"
 */
public class 两个字符串的删除操作 {
    public int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 复⽤前⽂计算 lcs ⻓度的函数
        int lcs = longestCommonSubsequence(s1, s2);
        return m - lcs + n - lcs;
    }
    // 计算最⻓公共⼦序列的⻓度
    int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(),n = s2.length();
        int[][] dp =new int[m+1][n+1];
        for (int i = 1; i <=m ; i++) {
            for (int j = 1; j <=n ; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    // s1[i-1] 和 s2[j-1] 必然在 lcs 中
                    dp[i][j] = 1 +dp[i-1][j-1];
                }else {
                    // s1[i-1] 和 s2[j-1] ⾄少有⼀个不在 lcs 中
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
