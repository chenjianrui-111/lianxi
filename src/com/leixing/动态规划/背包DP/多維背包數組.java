package com.leixing.动态规划.背包DP;

/**
 * 滚动数组
 * 根据「状态转移」可知，更新某个物品的状态时，只依赖于上一个物品的状态。
 * 因此，可以使用「滚动数组」的方式进行空间优化。
 * 代码（为了方便理解， 将第一件物品的处理单独抽了出来，也可以不抽出来，只需要将让物品下标从 1 开始即可，见 ）：
 */
public class 多維背包數組 {
    public int findMaxForm(String[] strs, int m, int n) {
        int len=strs.length;
        // 预处理每一个字符包含 0 和 1 的数量
        int [][] cnt=new int[len][2];
        for (int i=0;i<len;i++){
            String str=strs[i];
            int zero=0,one=0;
            for (char c:str.toCharArray()){
                if (c == '0') zero++;
                else one++;
            }
            cnt[i]=new int[]{zero,one};
        }
        // 处理只考虑第一件物品的情况
        // 「物品维度」修改为 2
        int[][][] f = new int[2][m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                f[0][i][j] = (i >= cnt[0][0] && j >= cnt[0][1]) ? 1 : 0;
            }
        }
        // 处理考虑其余物品的情况
        for (int k = 1; k < len; k++) {
            int zero = cnt[k][0], one = cnt[k][1];
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    // 不选择第 k 件物品
                    // 将 k-1 修改为 (k-1)&1
                    int a = f[(k-1)&1][i][j];
                    // 选择第 k 件物品（前提是有足够的 m 和 n 额度可使用）
                    // 将 k-1 修改为 (k-1)&1
                    int b = (i >= zero && j >= one) ? f[(k-1)&1][i-zero][j-one] + 1 : 0;
                    f[k&1][i][j] = Math.max(a, b);
                }
            }
        }
        // 将 len-1 修改为 (len-1)&1
        return f[(len-1)&1][m][n];
    }
}
