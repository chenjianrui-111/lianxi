package com.xiaochao.动态规划.股票买卖;

public class 含手续费冷冻期交易次数 {
    // 同时考虑交易次数的限制、冷冻期和⼿续费
    int maxProfit_all_in_one(int max_k, int[] prices, int cooldown, int fee) {
        int n = prices.length;
        if (n <= 0) {
            return 0;
        }
        if (max_k > n / 2) {
            // 交易次数 k 没有限制的情况
            return maxProfit_k_inf(prices, cooldown, fee);
        }
        int[][][] dp = new int[n][max_k + 1][2];
        // k = 0 时的 base case
        for (int i = 0; i < n; i++) {
            dp[i][0][1] = Integer.MIN_VALUE;
            dp[i][0][0] = 0;
        }
        for (int i = 0; i < n; i++)
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    // base case 1
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i] - fee;
                    continue;
                }
                // 包含 cooldown 的 base case
                if (i - cooldown - 1 < 0) {
                    // base case 2
                    dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] +
                            prices[i]);
                    // 别忘了减 fee
                    dp[i][k][1] = Math.max(dp[i-1][k][1], -prices[i] - fee);
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] +
                        prices[i]);
                // 同时考虑 cooldown 和 fee
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-cooldown-1][k-1][0]
                        - prices[i] - fee);
            }
        return dp[n - 1][max_k][0];
    }
    // k ⽆限制，包含⼿续费和冷冻期
    int maxProfit_k_inf(int[] prices, int cooldown, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // base case 1
                dp[i][0] = 0;
                dp[i][1] = -prices[i] - fee;
                continue;
            }
            // 包含 cooldown 的 base case
            if (i - cooldown - 1 < 0) {
                // base case 2
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                // 别忘了减 fee
                dp[i][1] = Math.max(dp[i-1][1], -prices[i] - fee);
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 同时考虑 cooldown 和 fee
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - cooldown - 1][0] -
                    prices[i] - fee);
        }
        return dp[n - 1][0];
    }
}
