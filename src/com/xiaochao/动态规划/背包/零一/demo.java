package com.xiaochao.动态规划.背包.零一;

/**
 * for i in [1..N]:
 *  for w in [1..W]:
 *  dp[i][w] = max(
 *  dp[i-1][w],
 *  dp[i-1][w - wt[i-1]] + val[i-1]
 *  )
 * return dp[N][W]
 */
public class demo {
    int knapsack(int W, int N, int[] wt, int[] val) {
        // base case 已初始化
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w - wt[i - 1] < 0) {
                    // 这种情况下只能选择不装⼊背包
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 装⼊或者不装⼊背包，择优
                    dp[i][w] = Math.max(
                            dp[i - 1][w - wt[i-1]] + val[i-1],
                            dp[i - 1][w]
                    );
                }
            }
        }

        return dp[N][W];
    }
}
