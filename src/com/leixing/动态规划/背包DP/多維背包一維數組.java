package com.leixing.动态规划.背包DP;

/**
 *事实上，我们还能继续进行空间优化。
 * 再次观察我们的「状态转移方程」发现： 不仅仅依赖于上一行，还明确依赖于比  小和比  小的状态。
 * 即可只依赖于「上一行」中「正上方」的格子，和「正上方左边」的格子。
 * 因此可直接参考「01 背包的空间优化」方式：取消掉「物品维度」，然后调整容量的遍历顺序。
 */
public class 多維背包一維數組 {
    public int findMaxForm(String[] strs, int m, int n) {
        int len=strs.length;
        int [][] cnt=new int[len][2];
        for (int i=0;i<len;i++){
            int zero=0,one=0;
            for (char c: strs[i].toCharArray()){
                if (c == '0') zero++;
                else one++;
            }
            cnt[i]=new int[]{zero,one};
        }
        int [][] f=new int[m+1][n+1];
        for (int k=0;k<len;k++){
            int zero=cnt[k][0],one=cnt[k][1];
            for (int i=m;i>=zero;i--){
                for (int j=n;j>=one;j--){
                    f[i][j]=Math.max(f[i][j],f[i-zero][j-one] + 1);
                }
            }
        }
        return f[m][n];
    }
}
