package com.xiaochao.动态规划;

import java.util.Arrays;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 */
public class 编辑距离 {
    public int minDistance(String word1, String word2) {
        int m =word1.length();
        int n =word2.length();
         memo = new int[m][n];
        for (int[] row :memo){
            Arrays.fill(memo,-1);
        }
        return dp(word1,m - 1,word2,n - 1);
    }
    int[][] memo;
    int dp(String word1,int i,String word2,int j){
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (memo[i][j] != -1){
            return memo[i][j];
        }
        //状态转移
        if (word1.charAt(i) == word2.charAt(j)){
            memo[i][j] = dp(word1,i - 1,word2, j - 1);
        }else {
            memo[i][j] = min(
                    dp(word1, i, word2, j - 1) + 1,//插入
                    dp(word1, i - 1, word2, j) + 1,//删除
                    dp(word1, i - 1, word2, j - 1) + 1 //替换 跳过
            );
        }
        return memo[i][j];
    }
    int min(int a,int b,int c){
        return Math.min(a,Math.min(b,c));
    }
}
class solution6{
    int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 定义：s1[0..i] 和 s2[0..j] 的最⼩编辑距离是 dp[i+1][j+1]
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        // ⾃底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }
        // 储存着整个 s1 和 s2 的最⼩编辑距离
        return dp[m][n];
    }
    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
