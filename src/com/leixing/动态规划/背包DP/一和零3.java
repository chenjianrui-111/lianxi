package com.leixing.动态规划.背包DP;

import java.util.logging.Level;

/**
 * 一维空间优化
 * 事实上，我们还能继续进行空间优化。
 * 再次观察我们的「状态转移方程」发现：f[k][i][j] 不仅仅依赖于上一行，还明确依赖于比 i 小和比 j 小的状态。
 * 即可只依赖于「上一行」中「正上方」的格子，和「正上方左边」的格子。
 * 对应到「朴素的 01 背包问题」依赖关系如图：
 * 因此可直接参考「01 背包的空间优化」方式：取消掉「物品维度」，然后调整容量的遍历顺序
 */
public class 一和零3 {
    public int findMaxForm(String[] strs, int m, int n) {
        int length=strs.length;
        int [][] cnt=new int[length][2];
        for (int i=0;i<length;i++){
            int zero=0,one=0;
            for (char c:strs[i].toCharArray()){
                if (c == '0') zero++;
                else one++;
            }
            cnt[i]=new int[]{zero,one};
        }

        int [][] f=new int[m+1][n+1];
        for (int k=0;k<length;k++){
            int zero=cnt[k][0],one=cnt[k][1];
            for (int i=m;i>=zero;i--){
                for (int j=n;j>=one;j--){
                    f[i][j] = Math.max(f[i][j],f[i-zero][j-one] + 1);
                }
            }
        }
        return f[m][n];
    }
}
