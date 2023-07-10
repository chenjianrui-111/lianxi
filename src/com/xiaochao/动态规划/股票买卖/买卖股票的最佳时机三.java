package com.xiaochao.动态规划.股票买卖;

/**
 * k = 2 和前⾯题⽬的情况稍微不同，因为上⾯的情况都和 k 的关系不太⼤：要么 k 是正⽆穷，状态转移和 k 没关系了；要么 k = 1，跟 k = 0 这个 base case 挨得近，最后也没有存在感。 这道题 k = 2 和后⾯要讲的 k 是任意正整数的情况中，对 k 的处理就凸显出来了，我们直接写代码，边写 边分析原因。 原始的状态转移⽅程，没有可化简的地⽅
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 */
public class 买卖股票的最佳时机三 {
    // 原始版本
    int maxProfit_k_2(int[] prices) {
        int max_k = 2, n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    // 处理 base case
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] +
                        prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] -
                        prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][max_k][0];
    }
}
