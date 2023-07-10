package com.xiaochao.DP;

/**
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 * 示例 1：
 * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * 输出：2
 * 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 * 总的来说，有两种计划。
 * 示例 2：
 * 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * 输出：7
 * 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 * 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 * 提示：
 * 1 <= n <= 100
 * 0 <= minProfit <= 100
 * 1 <= group.length <= 100
 * 1 <= group[i] <= 100
 * profit.length == group.length
 * 0 <= profit[i] <= 100
 */

/**
 * 动态规划
 * 这是一类特殊的多维费用背包问题。
 * 将每个任务看作一个「物品」，完成任务所需要的人数看作「成本」，完成任务得到的利润看作
 * 「价值」。
 * 其特殊在于存在一维容量维度需要满足「不低于」，而不是常规的「不超过」。这需要我们对于
 * 某些状态作等价变换。
 * 定义 f[i][j][k] 为考虑前 i 件物品，使用人数不超过 j，所得利润至少为 k 的方案数。
 * 对于每件物品（令下标从 1 开始），我们有「选」和「不选」两种决策：
 * • 不选：显然有：f[i - 1][j][k]
 * • 选：首先需要满足人数达到要求（ j >= group[i − 1] ），还需要考虑「至少利
 * 润」负值问题：
 * 如果直接令「利润维度」为 k − profit[i − 1] 可能会出现负值，那么负值是否为
 * 合法状态呢？这需要结合「状态定义」来看，由于是「利润至少为 k」，因此属于
 * 「合法状态」，需要参与转移。
 * 由于我们没有设计动规数组存储「利润至少为负权」状态，我们需要根据「状态定
 * 义」做一个等价替换，将这个「状态」映射到 f[i][j][0]。这主要是利用所有的任务
 * 利润都为“非负数”，所以不可能出现利润为负的情况，这时候「利润至少为某个负 数 k」的方案数其实是完全等价于「利润至少为 0」的方案数。
 * 最终 f[i][j][k] 为上述两种情况之和.
 * 然后考虑「如何构造有效起始值」问题，还是结合我们的「状态定义」来考虑：
 * 当不存在任何物品（任务）时，所得利用利润必然为 0（满足至少为 0），同时对人数限制没有要求。
 * 因此可以让所有 f[0][x][0] = 1。
 */
public class 盈利计划 {
    int MOD = (int) (1e9 + 7);
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        long[][][] f= new long[m + 1][n + 1][minProfit + 1];
        for (int i = 0; i <= n ; i++) {
            f[0][i][0] = 1;
        }
        for (int i = 1; i <= m ; i++) {
            int a = group[i - 1], b = profit[i - 1];
            for (int j = 0; j <= n ; j++) {
                for (int k = 0; k <= minProfit ; k++) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= a){
                        int u = Math.max(k - b,0);
                        f[i][j][k] += f[i - 1][j - a][u];
                        f[i][j][k] %= MOD;
                    }
                }
            }
        }
        return (int) f[m][n][minProfit];
    }
}

class Solution16 {
    int mod = (int) 1e9 + 7;

    public int profitableSchemes(int n, int min, int[] gs, int[] ps) {
        int m = gs.length;
        int[][] f = new int[n + 1][min + 1];
        for (int i = 0; i <= n; i++) f[i][0] = 1;
        for (int i = 1; i <= m; i++) {
            int a = gs[i - 1], b = ps[i - 1];
            for (int j = n; j >= a; j--) {
                for (int k = min; k >= 0; k--) {
                    int u = Math.max(k - b, 0);
                    f[j][k] += f[j - a][u];
                    if (f[j][k] >= mod) f[j][k] -= mod;
                }
            }
        }
        return f[n][min];
    }
}
//• 时间复杂度：O(m ∗ n ∗ min) • 空间复杂度：O(m ∗ n ∗ min)
