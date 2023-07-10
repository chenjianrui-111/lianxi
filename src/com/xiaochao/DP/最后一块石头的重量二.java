package com.xiaochao.DP;

/**
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * 示例 1：
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * 示例 2：
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 * 提示：
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 */

/**
 * 动态规划
 * 有了上述分析后，问题转换为：为 stones 中的每个数字添加 +/−，使得形成的「计算表达
 * 式」结果绝对值最小。
 * 与（题解）494. 目标和 类似，需要考虑正负号两边时，其实只需要考虑一边就可以了，使用总
 * 和 sum 减去决策出来的结果，就能得到另外一边的结果。
 * 同时，由于想要「计算表达式」结果绝对值，因此我们需要将石子划分为差值最小的两个堆。
 * 其实就是对「计算表达式」中带 − 的数值提取公因数 −1，进一步转换为两堆石子相减总和，绝对值最小。
 * 这就将问题彻底切换为 01 背包问题：从 stones 数组中选择，凑成总和不超过 su2m 的最大价值。
 * 其中「成本」&「价值」均为数值本身。
 * 整理一下：
 * 定义 f[i][j] 代表考虑前 i 个物品（数值），凑成总和不超过 j 的最大价值。
 * 每个物品都有「选」和「不选」两种决策，转移方程为：
 * f[i][j] = max(f[i − 1][j], f[i − 1][j − stones[i − 1]] + stones[i − 1])
 * 与完全背包不同，01 背包的几种空间优化是不存在时间复杂度上的优化，因此写成 朴素二维、
 * 滚动数组、一维优化 都可以。
 * 建议直接上手写「一维空间优化」版本，是其他背包问题的基础。
 */
public class 最后一块石头的重量二 {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int t = sum / 2;
        int[][] f =new int[n + 1][t + 1];
        for (int i = 1; i <= n ; i++) {
            int x = stones[i - 1];
            for (int j = 0; j <= t ; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= x){
                    f[i][j] = Math.max(f[i][j],f[i - 1][j - x] + x);
                }
            }
        }
        return Math.abs(sum - f[n][t] - f[n][t]);
    }
}

class Solution17 {
    public int lastStoneWeightII(int[] ss) {
        int n = ss.length;
        int sum = 0;
        for (int i : ss) sum += i;
        int t = sum / 2;
        int[][] f = new int[2][t + 1];
        for (int i = 1; i <= n; i++) {
            int x = ss[i - 1];
            int a = i & 1, b = (i - 1) & 1;
            for (int j = 0; j <= t; j++) {
                f[a][j] = f[b][j];
                if (j >= x) f[a][j] = Math.max(f[a][j], f[b][j - x] + x);
            }
        }
        return Math.abs(sum - f[n & 1][t] - f[n & 1][t]);
    }
}

class Solution18 {
    public int lastStoneWeightII(int[] ss) {
        int n = ss.length;
        int sum = 0;
        for (int i : ss) sum += i;
        int t = sum / 2;
        int[] f = new int[t + 1];
        for (int i = 1; i <= n; i++) {
            int x = ss[i - 1];
            for (int j = t; j >= x; j--) {
                f[j] = Math.max(f[j], f[j - x] + x);
            }
        }
        return Math.abs(sum - f[t] - f[t]);
    }
}
//• 时间复杂度：O(n ∗ ∑i=0 n−1 stones[i])
//• 空间复杂度：O(n ∗ ∑i=0 n−1 stones[i])
