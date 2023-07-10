package com.xiaochao.笔试;

public class Main2 {
    public static void main(String[] args) {
        String  A= "ABCDZJUDCBA";
        int i = longestPalindromeSubseq(A);
        System.out.println(i);
    }
    public static int longestPalindromeSubseq(String s) {
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
