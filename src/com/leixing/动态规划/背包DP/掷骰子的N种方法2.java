package com.leixing.动态规划.背包DP;

/**
 * 一维空间优化
 * 更进一步，利用「f[i][j] 明确只依赖于 f[i - 1][x]，且 x < j」，我们能通过「01 背包」一维空间优化方式：将物品维度取消，调整容量维度遍历顺序为「从大到小」。
 */
public class 掷骰子的N种方法2 {
    int mod = (int)1e9+7;
    public int numRollsToTarget(int n, int m, int target) {
        int [] f=new int[target+1];
        f[0]=1;
        for (int i=1;i<=n;i++){
            for (int j=target;j>=0;j--){
                f[j]=0;
                for (int k=1;k<=m;k++){
                    if (j>=k){
                        f[j]=(f[j] + f[j - k]) %mod;
                    }
                }
            }
        }
        return f[target];
    }
}
//时间复杂度：O(n * m * t)
//空间复杂度：O(t)
