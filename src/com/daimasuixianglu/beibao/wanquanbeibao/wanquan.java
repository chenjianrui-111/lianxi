package com.daimasuixianglu.beibao.wanquanbeibao;

public class wanquan {
    public int maxValue(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[N][C + 1];
        // 先预处理第一件物品
        for (int j = 0; j <= C; j++) {
            // 显然当只有一件物品的时候，在容量允许的情况下，能选多少件就选多少件
            int maxK = j / v[0];
            dp[0][j] = maxK * w[0];
        }
        // 处理剩余物品
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= C; j++) {
                // 不考虑第 i 件物品的情况（选择 0 件物品 i）
                int n = dp[i - 1][j];
                // 考虑第 i 件物品的情况
                int y = 0;
                for (int k = 1 ;; k++) {
                    if (j < v[i] * k) {
                        break;
                    }
                    y = Math.max(y, dp[i - 1][j - k * v[i]] + k * w[i]);
                }
                dp[i][j] = Math.max(n, y);
            }
        }
        return dp[N - 1][C];
    }
}
//时间复杂度：共有 N*C 个状态需要被转移，但每次转移都需要枚举当前物品的件数，最多枚举 C 次（重量为最小值 1），因此整体复杂度为O(N*C*C)
//空间复杂度：O(N*C)
class Solution {
    public int maxValue(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[2][C + 1];

        // 先预处理第一件物品
        for (int j = 0; j <= C; j++) {
            // 显然当我们只有一件物品的时候，在容量允许的情况下，能选多少件就选多少件
            int maxK = j / v[0];
            dp[0][j] = maxK * w[0];
        }

        // 处理剩余物品
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= C; j++) {
                // 不考虑第 i 件物品的情况（选择 0 件物品 i）
                int n = dp[(i - 1)&1][j];
                // 考虑第 i 件物品的情况
                int y = 0;
                for (int k = 1 ;; k++) {
                    if (j < v[i] * k) {
                        break;
                    }
                    y = Math.max(y, dp[(i - 1)&1][j - k * v[i]] + k * w[i]);
                }
                dp[i&1][j] = Math.max(n, y);
            }
        }
        return dp[(N - 1)&1][C];
    }
}
//时间复杂度：共有 N*C 个状态需要被转移，但每次转移都需要枚举当前物品的件数，最多枚举 C 次（重量为最小值 1），因此整体复杂度为O(N*C*C)
//空间复杂度：O(N*C)

/**
 *总结一下。
 * 0-1 背包问题的状态转换方程是：
 * 由于计算 dp[i][j] 的时候，依赖于dp[i-1][j-v[i]] 。
 * 因此我们在改为「一维空间优化」时，需要确保dp[j-v[i]]  存储的是上一行的值，即确保dp[j-v[i]]  还没有被更新，所以遍历方向是从大到小。
 * 完全背包问题的状态转移方程是：dp[i][j]=max(dp[i-1][j],dp[i][j-v[i]]+w[i])
 * 由于计算dp[i][j]  的时候，依赖于dp[i][j-v[i]] 。
 * 因此我们在改为「一维空间优化」时，需要确保 dp[j-v[i]] 存储的是当前行的值，即确保dp[j-v[i]]  已经被更新，所以遍历方向是从小到大。
 */
class Solution01 {
    public int maxValue(int N, int C, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= C; j++) {
                // 不考虑第 i 件物品的情况（选择 0 件物品 i）
                int n = dp[j];
                // 考虑第 i 件物品的情况
                int y = j - v[i] >= 0 ? dp[j - v[i]] + w[i] : 0;
                dp[j] = Math.max(n, y);
            }
        }
        return dp[C];
    }
}
//时间复杂度：共有 N*C 个状态需要被转移，复杂度为O(N*C)
//空间复杂度：O(C)
//形式上，我们只需要将 01 背包问题的「一维空间优化」解法中的「容量维度」遍历方向从「从大到小 改为 从小到大」就可以解决完全背包问题。
//但本质是因为两者进行状态转移时依赖了不同的格子：
//01 背包依赖的是「上一行正上方的格子」和「上一行左边的格子」。
//完全背包依赖的是「上一行正上方的格子」和「本行左边的格子」。
//另外，我们可以发现通过「一维空间优化」方式，可以将求解「完全背包」问题的时间复杂度从O(N*C*C)  降低为O(N*C) 。
