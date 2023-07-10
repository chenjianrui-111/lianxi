package com.leixing.动态规划.背包DP;

/**
 * 滚动数组
 * 根据状态转移方程，不难发现 f[i][j]只依赖于f[i - 1][x] ，且 j>=x.
 * 将 O(N * C) 的空间优化到O(C) 。
 */
public class 分組背包2 {
    public int maxValue(int N, int C, int[] S, int[][] v, int[][] w) {
        int [][] dp=new int[2][C+1];
        for (int i=1;i<=N;i++){
            int[] vi = v[i - 1];
            int[] wi = w[i - 1];
            int si = S[i - 1];
            for (int j = 1; j <= C; j++) {
                int a=i & 1,b=(i - 1) & 1;
                dp[i][j] =dp[b][j];
                for (int k=0;k<=si;k++){
                    if (j >= vi[k]){
                        dp[a][j] = Math.max(dp[a][j],dp[b][j -vi[k]] + wi[k]);
                    }
                }
            }
        }
        return dp[N & 1][C];
    }
}
