package com.daimasuixianglu.beibao.wanquanbeibao;

/**
 * 给定不同面额的硬币和一个总金额。
 * 写出函数来计算可以凑成总金额的硬币组合数。
 * 假设每一种面额的硬币有无限个。
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 */
public class 零钱兑换二 {
    public int change(int cnt, int[] cs) {
        int n=cs.length;
        int[][] f=new int[n+1][cnt+1];
        f[0][0]=1;
        for (int i = 1; i <=n ; i++) {
            int val=cs[i-1];
            for (int j = 0; j <=cnt ; j++) {
                f[i][j]=f[i-1][j];
                for (int k = 1; k *val<=j ; k++) {
                    f[i][j]=f[i-1][j-k*val];
                }
            }
        }
        return f[n][cnt];
    }
}
/**
 *在二维解法的基础上，直接取消「物品维度」
 * 确保「容量维度」的遍历顺序为「从小到大」（适用于「完全背包」）
 * 将形如 f[i-1][j-k*val] 的式子更替为 f[j-val]，同时解决「数组越界」问题（将物品维度的遍历修改为从 val 开始）
 */
class Solution05 {
    public int change(int cnt, int[] cs) {
        int n = cs.length;
        int[] f = new int[cnt + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            int val = cs[i - 1];
            for (int j = val; j <= cnt; j++) {
                f[j] += f[j - val];
            }
        }
        return f[cnt];
    }
}
//时间复杂度：共有 n*cnt 个状态需要转移，整体复杂度为O(n*cnt) 。
//空间复杂度：O(cnt)。
