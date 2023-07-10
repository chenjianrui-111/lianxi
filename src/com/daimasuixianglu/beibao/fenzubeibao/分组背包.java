package com.daimasuixianglu.beibao.fenzubeibao;

/**
 * 给定 N 个物品组，和容量为 C  的背包。
 * 第  i个物品组共有S[i]  件物品，其中第  i 组的第  j 件物品的成本为v[i][j] ，价值为 w[i][j]。
 * 每组有若干个物品，同一组内的物品最多只能选一个。
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 * 示例：
 * 输入：N = 2, C = 9, S = [2, 3], v = [[1,2,-1],[1,2,3]], w = [[2,4,-1],[1,3,6]]
 * 输出：10
 */
public class 分组背包 {
    public int maxValue(int N, int C, int[] S, int[][] v, int[][] w) {
        int[][] dp=new int[N+1][C+1];
        for (int i = 1; i <=N ; i++) {
            int[]vi=new int[i-1];
            int[]wi=new int[i-1];
            int val=S[i-1];
            for (int j = 1; j <=C ; j++) {
                dp[i][j]=dp[i-1][j];
                for (int k = 0; k <val ; k++) {
                    if (j>=vi[k]){
                        dp[i][j]=Math.max(dp[i][j],dp[i-1][j-vi[k]]+wi[k]);
                    }
                }
            }
        }
        return dp[N][C];
    }
}
class Solution01{
    public int maxValue(int N, int C, int[] S, int[][] v, int[][] w) {
        int[][] dp=new int[2][C+1];
        for (int i = 1; i <=N ; i++) {
            int vi[]=v[i-1];
            int wi[]=w[i-1];
            int val=S[i-1];
            for (int j = 1; j <=C ; j++) {
                int a=i&1,b=(i-1)&1;
                dp[a][j]=dp[b][j];
                for (int k = 0; k <val ; k++) {
                    if (j>=vi[k]){
                        dp[i][j]=Math.max(dp[a][j],dp[b][j-vi[k]]+wi[k]);
                    }
                }
            }
        }
        return dp[N][C];
    }
}
class Solution02 {
    public int maxValue(int N, int C, int[] S, int[][] v, int[][] w) {
        int[] dp = new int[C + 1];
        for (int i = 1; i <= N; i++) {
            int[] vi = v[i - 1];
            int[] wi = w[i - 1];
            int si = S[i - 1];
            for (int j = C; j >= 0; j--) {
                for (int k = 0; k < si; k++) {
                    if (j >= vi[k]) {
                        dp[j] = Math.max(dp[j], dp[j - vi[k]] + wi[k]);
                    }
                }
            }
        }
        return dp[C];
    }
}
