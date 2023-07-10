package com.leixing.动态规划.背包DP;

/**
 *一维空间优化
 * 进一步，我们可以用「01 背包」类似的思路，进行一维空间优化：
 * 取消物品维度；
 * 将容量维度的遍历顺序修改为「从大到小」（确保所依赖的值不会被覆盖）
 */
public class 分組背包3 {
    public int maxValue(int N, int C, int[] S, int[][] v, int[][] w) {
        int [] f=new int[C+1];
        for (int i=1;i<=N;i++){
            int[] vi = v[i - 1];
            int[] wi = w[i - 1];
            int si = S[i - 1];
            for (int j=C;j>=0;j--){
                for (int k=0;k<si;k++){
                    if (j >= vi[k]) {
                        f[j] = Math.max(f[j], f[j - vi[k]] + wi[k]);
                    }
                }
            }
        }
        return f[C];
    }
}
