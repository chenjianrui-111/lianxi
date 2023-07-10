package com.xiaochao.动态规划;

import java.util.Arrays;

/**
 * 输⼊：s1 = "sea", s2 = "eat"
 * 输出：231
 * 解释：在 "sea" 中删除 "s" 并将 "s" 的值 (115) 加⼊总和。 在 "eat" 中删除 "t" 并将 116 加⼊总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最⼩和。
 */
public class 两个字符串的最小阿斯克码删除和 {

    int memo[][];
    public int minimumDeleteSum(String s1, String s2) {
        int m =s1.length(),n = s2.length();
        memo = new int[m][n];
        for (int[] row :memo){
            Arrays.fill(row,-1);
        }
        return dp(s1,0,s2,0);
    }
    // 定义：将 s1[i..] 和 s2[j..] 删除成相同字符串，
    // 最⼩的 ASCII 码之和为 dp(s1, i, s2, j)。
    int dp(String s1,int i,String s2,int j){
        int res = 0;
        if (i == s1.length()){
            for (;j < s2.length();j++){
                res += s2.charAt(j);
            }
            return res;
        }
        if (j == s2.length()){
            for (;i<s1.length();i++){
                res += s1.charAt(i);
            }
            return res;
        }
        if (memo[i][j] != -1){
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)){
            memo[i][j] = dp(s1, i + 1, s2, j + 1);
        }else {
            // s1[i] 和 s2[j] ⾄少有⼀个不在 lcs 中，删⼀个
            memo[i][j] = Math.min(
                    s1.charAt(i) + dp(s1, i + 1, s2, j),
                        s2.charAt(j) + dp(s1, i, s2, j + 1)
            );
        }
        return memo[i][j];
    }

    public int minimumDeleteSum2(String s1, String s2) {
        int m = s1.length(),n= s2.length();
        //dp[i][j] 表示子串 s1[0..i] 和 s2[0..j] 最小 ASCII 删除和
        int[][] dp = new int[m+1][n+1];
        //base case
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) dp[i][0] += dp[i - 1][0] + s1.charAt(i - 1);
        for (int i = 1; i <= n; i++) dp[0][i] += dp[0][i - 1] + s2.charAt(i - 1);

        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
              if (s1.charAt(i) == s2.charAt(j)){
                  dp[i][j] = 1 + dp[i-1][j-1];
              }else {
                  dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
              }
            }
        }
        return dp[m][n];
    }
}
