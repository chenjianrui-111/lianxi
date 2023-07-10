package com.daimasuixianglu.beibao.fenzubeibao;

/**
 * 这里有 d 个一样的骰子，每个骰子上都有 f 个面，分别标号为 1,2,...,f。
 * 我们约定：掷骰子的得到总点数为各骰子面朝上的数字的总和。
 * 如果需要掷出的总点数为 target，请你计算出有多少种不同的组合情况（所有的组合情况总共有  种），模  后返回。
 * 示例 1：
 * 输入：d = 1, f = 6, target = 3
 * 输出：1
 * 分组背包
 * 在 分组背包问题 中我们提到，分组背包不仅仅有「组内物品最多选择一个」的情况，还存在「组内物品必须选择一个」的情况。
 * 对于本题，可以将每个骰子看作一个物品组，且每次 必须 从物品组中选择一个物品（所掷得的数值大小视作具体物品）。
 * 这样就把问题转换为：用 d 个骰子（物品组）进行掷，掷出总和（取得的总价值）为 t 的方案数。
 * 虽然，我们还没专门讲过「背包问题求方案数」，但基本分析与「背包问题求最大价值」并无本质区别。
 * 我们可以套用「分组背包求最大价值」的状态定义来微调：f[i][j] 表示考虑前 i 个物品组，凑成价值为 j 的方案数。
 * 为了方便，我们令物品组的编号从  1开始，因此有显而易见的初始化条件 f[0][0]=1 。
 * 代表在不考虑任何物品组的情况下，只有凑成总价值为 0 的方案数为 1 ，凑成其他总价值的方案不存在。
 * 不失一般性考虑 f[i][j] 该如何转移，也就是考虑第 i 个物品组有哪些决策。
 * 根据题意，对于第 i 个物品组而言，可能决策的方案有：
 * 第 i 个骰子的结果为 1，有 f[i][j]=f[i-1][j-1]
 * 第 i 个骰子的结果为 2 ，有 f[i][j]=f[i-1][j-2]
 * ...
 * 第 i 个骰子的结果为  m ，有f[i][j]=f[i-1][j-m]
 *  则是上述所有可能方案的方案数总和，即有：
 */
public class 掷骰子的N种方法 {
    int mod= (int) (1e9+7);
    public int numRollsToTarget(int n, int m, int t) {
        int [][] dp=new int[n+1][t+1];
        dp[0][0]=1;
        //枚举物品组
        for (int i = 1; i <=n ; i++) {
            // 枚举背包容量（所掷得的总点数）
            for (int j = 0; j <= t ; j++) {
                // 枚举决策（当前骰子所掷得的点数）
                for (int k = 1; k <= m; k++) {
                    if (j>=k){
                        dp[i][j]=(dp[i][j]+dp[i-1][j-k]) % mod;
                    }
                }
            }
        }
        return dp[n][t];
    }
}
//时间复杂度：O(n*m*t)
//空间复杂度：O(n*t)

class Solution {
    int mod = (int)1e9+7;
    public int numRollsToTarget(int n, int m, int t) {
        int[][] f = new int[2][t + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int a = i & 1, b = (i - 1) & 1;
            for (int j = 0; j <= t; j++) {
                f[a][j] = 0; // 先手动置零
                for (int k = 1; k <= m; k++) {
                    if (j >= k) {
                        f[a][j] = (f[a][j] + f[b][j-k]) % mod;
                    }
                }
            }
        }
        return f[n&1][t];
    }
}

class Solution03{
    int mod = (int)1e9+7;
    public int numRollsToTarget(int n, int m, int t) {
        int[] f=new int[t+1];
        f[0]=1;
        for (int i = 1; i <=n ; i++) {
            for (int j = t; j >=0 ; j--) {
                f[j]=0;
                for (int k = 1; k <= m ; k++) {
                    f[j]=(f[j]+f[j-k])%mod;
                }
            }
        }
        return f[t];
    }
}
//时间复杂度：O(N*M*T)
//空间复杂度：O(T)
