package com.daimasuixianglu.beibao.zeroone;

//dp[N][C+1]  解法
public class zeroone背包 {
    public int maxValue(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[N][C+1];
        // 先处理「考虑第一件物品」的情况
        for (int i = 0; i <= C; i++) {
            dp[0][i] = i >= v[0] ? w[0] : 0;
        }
        // 再处理「考虑其余物品」的情况
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < C + 1; j++) {
                // 不选该物品
                int n = dp[i-1][j];
                // 选择该物品，前提「剩余容量」大于等于「物品体积」
                int y = j >= v[i] ? dp[i-1][j-v[i]] + w[i] : 0;
                dp[i][j] = Math.max(n, y);
            }
        }
        return dp[N-1][C];
    }
}
//时间复杂度：共有N*C  个状态需要被转移，复杂度为O(N*C)
//空间复杂度：O(N*C)
/**
 *dp[2][C+1] 解法
 * 根据「转移方程」，我们知道计算第  行格子只需要第  行中的某些值。
 * 也就是计算「某一行」的时候只需要依赖「前一行」。
 * 因此可以用一个只有两行的数组来存储中间结果，根据当前计算的行号是偶数还是奇数来交替使用第 0 行和第 1 行。
 * 这样的空间优化方法称为「滚动数组」，我在 路径问题 第四讲 也曾与你分享过。
 * 这种空间优化方法十分推荐，因为改动起来没有任何思维难度。
 * 只需要将代表行的维度修改成 2，并将所有使用行维度的地方从 i 改成 i%2 或者 i&1 即可（更建议使用 ，& 运算在不同 CPU 架构的机器上要比 % 运算稳定）。
 */
class Solution {
    public int maxValue(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[2][C+1];
        // 先处理「考虑第一件物品」的情况
        for (int i = 0; i < C + 1; i++) {
            dp[0][i] = i >= v[0] ? w[0] : 0;
        }
        // 再处理「考虑其余物品」的情况
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < C + 1; j++) {
                // 不选该物品
                int n = dp[(i-1)&1][j];
                // 选择该物品
                int y = j >= v[i] ? dp[(i-1)&1][j-v[i]] + w[i] : 0;
                dp[i&1][j] = Math.max(n, y);
            }
        }
        return dp[(N-1)&1][C];
    }
}
//时间复杂度：共有 N*C  个状态需要被转移，复杂度为O(N*C)
//空间复杂度：O(C)

/**
 * dp[C+1] 解法
 * 不难发现当求解第  行格子的值时，不仅是只依赖第 i-1 行，还明确只依赖第i-1  行的第 c 个格子和第 c-v[i] 个格子（也就是对应着第 i 个物品不选和选的两种情况）。
 * 换句话说，只依赖于「上一个格子的位置」以及「上一个格子的左边位置」。
 *因此，只要我们将求解第  行格子的顺序「从 0 到 c 」改为「从 c 到 0」，就可以将原本 2 行的二维数组压缩到一行（转换为一维数组）。
 * 这样做的空间复杂度和「滚动数组」优化的空间复杂度是一样的。但仍然具有意义，而且这样的「一维空间」优化，是求解其他背包问题的基础，需要重点掌握。
 */
class Solution01 {
    public int maxValue(int N, int C, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        for (int i = 0; i < N; i++) {
            for (int j = C; j >= v[i]; j--) {
                // 不选该物品
                int n = dp[j];
                // 选择该物品
                int y = dp[j-v[i]] + w[i];
                dp[j] = Math.max(n, y);
            }
        }
        return dp[C];
    }
}
