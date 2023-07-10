package com.daimasuixianglu.beibao.zeroone;

/**
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * 示例 1：
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 */
public class 最后一块石头的重量二 {
    public int lastStoneWeightII(int[] ss) {
        int n=ss.length;
        int sum=0;
        for (int i:ss) sum+=i;
        int t=sum / 2;
        int[][] f=new int[n+1][t+1];
        for (int i = 1; i <=n ; i++) {
            int u=ss[i-1];
            for (int j = 0; j <=t ; j++) {
                f[i][j]=f[i-1][j];
                if (j>=u){
                    f[i][j]=Math.min(f[i][j],f[i-1][j-u]+u);
                }
            }
        }
        return Math.abs(sum - f[n][t] - f[n][t]);
    }
}
class Solution02 {
    public int lastStoneWeightII(int[] ss) {
        int n = ss.length;
        int sum = 0;
        for (int i : ss) sum += i;
        int t = sum / 2;
        int[][] f = new int[2][t + 1];
        for (int i = 1; i <= n; i++) {
            int x = ss[i - 1];
            int a = i & 1, b = (i - 1) & 1;
            for (int j = 0; j <= t; j++) {
                f[a][j] = f[b][j];
                if (j >= x) f[a][j] = Math.max(f[a][j], f[b][j - x] + x);
            }
        }
        return Math.abs(sum - f[n&1][t] - f[n&1][t]);
    }
}
class Solution03 {
    public int lastStoneWeightII(int[] ss) {
        int n = ss.length;
        int sum = 0;
        for (int i : ss) sum += i;
        int t = sum / 2;
        int[] f = new int[t + 1];
        for (int i = 1; i <= n; i++) {
            int x = ss[i - 1];
            for (int j = t; j >= x; j--) {
                f[j] = Math.max(f[j], f[j - x] + x);
            }
        }
        return Math.abs(sum - f[t] - f[t]);
    }
}
