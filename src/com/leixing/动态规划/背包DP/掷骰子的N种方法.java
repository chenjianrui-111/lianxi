package com.leixing.动态规划.背包DP;

/**
 * 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
 * 给定三个整数 n ,  k 和 target ，返回可能的方式(从总共 kn 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。
 * 答案可能很大，你需要对 10^9 + 7 取模 。
 * 示例 1：
 * 输入：n = 1, k = 6, target = 3
 * 输出：1
 * 解释：你扔一个有6张脸的骰子。
 * 得到3的和只有一种方法。
 * 示例 2：
 * 输入：n = 2, k = 6, target = 7
 * 输出：6
 * 解释：你扔两个骰子，每个骰子有6个面。
 * 得到7的和有6种方法1+6 2+5 3+4 4+3 5+2 6+1。
 * 示例 3：
 * 输入：n = 30, k = 30, target = 500
 * 输出：222616187
 * 解释：返回的结果必须是对 10^9 + 7 取模。
 * 提示：
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 * 分组背包
 * 在 分组背包问题 中我们提到，分组背包不仅仅有「组内物品最多选择一个」的情况，还存在「组内物品必须选择一个」的情况。
 * 对于本题，可以将每个骰子看作一个物品组，且每次 必须 从物品组中选择一个物品（所掷得的数值大小视作具体物品）。
 * 这样就把问题转换为：用 d 个骰子（物品组）进行掷，掷出总和（取得的总价值）为 t 的方案数。
 * 虽然，我们还没专门讲过「背包问题求方案数」，但基本分析与「背包问题求最大价值」并无本质区别。
 * 我们可以套用「分组背包求最大价值」的状态定义来微调：f[i][j]表示考虑前 i 个物品组，凑成价值为 j 的方案数。
 * 为了方便，我们令物品组的编号从 1 开始，因此有显而易见的初始化条件 f[0][0] = 1。
 * 代表在不考虑任何物品组的情况下，只有凑成总价值为 0 的方案数为 1，凑成其他总价值的方案不存在。
 * 不失一般性考虑 f[i][j] 该如何转移，也就是考虑第 i 个物品组有哪些决策。
 * 根据题意，对于第 i 个物品组而言，可能决策的方案有：
 * 第 i 个骰子的结果为 1，有 f[i][j] = f[i - 1][j - 1]
 * 第 i 个骰子的结果为 2，有 f[i][j] = f[i - 1][j - 2]
 * ...
 * 第 i 个骰子的结果为 m，有 f[i][j] = f[i - 1][j - m]
 * f[i][j]则是上述所有可能方案的方案数总和，即有：
 * f[i][j] = \sum_{k = 1}^{m}f[i - 1][j - k], j >= k
 */
public class 掷骰子的N种方法 {
    int mod= (int) (1e9+7);
    public int numRollsToTarget(int n, int k, int target) {
        int [][] f=new int[n+1][target+1];
        f[0][0] =1;
        // 枚举物品组（每个骰子）
        for (int i=1;i<=n;i++){
            // 枚举背包容量（所掷得的总点数）
            for (int j=0;j<=target;j++){
                // 枚举决策（当前骰子所掷得的点数）
                for (int m=1;m<=k;m++){
                    if (j>m){
                        f[i][j]=(f[i][j]+f[i-1][j-k]) %mod;
                    }
                }
            }
        }
        return f[n][target];
    }
}
//时间复杂度：O(n * m * t)
//空间复杂度：O(n * t)
