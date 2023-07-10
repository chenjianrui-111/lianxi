package com.daimasuixianglu.beibao.wanquanbeibao;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 */
public class 零钱兑换 {
    int INF=Integer.MAX_VALUE;
    public int coinChange(int[] cs, int cnt) {
        int n=cs.length;
        int[][] f=new int[n+1][cnt+1];
        // 初始化（没有任何硬币的情况）：只有 f[0][0] = 0；其余情况均为无效值。
        // 这是由「状态定义」决定的，当不考虑任何硬币的时候，只能凑出总和为 0 的方案，所使用的硬币数量为 0
        for (int i = 0; i <=cnt ; i++) {
            f[0][i]=INF;
        }
        //有硬币的情况
        for (int i = 1; i <=n ; i++) {
            int val=cs[i-1];
            for (int j = 0; j <=cnt ; j++) {
                //不考虑当前硬币的使用情况
                f[i][j]=f[i-1][j];
                //考虑当前硬币的情况
                for (int k = 1; k * val<=j ; k++) {
                    if (f[i-1][j-k*val] !=INF){
                        f[i][j]=Math.min(f[i][j],f[i-1][j-k*val]+k);
                    }
                }
            }
        }
        return f[n][cnt]== INF ? -1 :f[n][cnt];
    }
}

class Solution03 {
    int INF = 0x3f3f3f3f;
    public int coinChange(int[] cs, int cnt) {
        int n = cs.length;
        int[] f = new int[cnt + 1];
        for (int i = 1; i <= cnt; i++) f[i] = INF;
        for (int i = 1; i <= n; i++) {
            int val = cs[i - 1];
            for (int j = val; j <= cnt; j++) {
                f[j] = Math.min(f[j], f[j - val] + 1);
            }
        }
        return f[cnt] == INF ? -1 : f[cnt];
    }
}
//时间复杂度：共有 n*cnt  个状态需要转移，整体复杂度为O(n*cnt) 。
//
//空间复杂度O(cnt)：。
