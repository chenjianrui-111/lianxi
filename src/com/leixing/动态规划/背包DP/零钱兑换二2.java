package com.leixing.动态规划.背包DP;

/**
 *完全背包（一维优化）
 * 显然二维完全背包求解方案复杂度有点高。
 * n 的数据范围为 10^2
 * cnt 的数据范围为 10^3
 * 总的计算量为 10^8
 * 以上，处于超时边缘（实际测试可通过）。
 * 我们需要对其进行「降维优化」，可以使用最开始讲的 数学分析方式，或者上一讲讲的 换元优化方式 进行降维优化。
 * 由于 数学分析方式 十分耗时，我们用得更多的 换元优化方式。两者同样具有「可推广」特性。
 * 因为后者更为常用，所以我们再来回顾一下如何进行「直接上手写一维空间优化的版本」 ：
 * 在二维解法的基础上，直接取消「物品维度」
 * 确保「容量维度」的遍历顺序为「从小到大」（适用于「完全背包」）
 * 将形如 f[i - 1][j - k * val] 的式子更替为 f[j - val]，同时解决「数组越界」问题（将物品维度的遍历修改为从 val 开始）
 */
public class 零钱兑换二2 {
    public int change(int amount, int[] coins) {
        int len=coins.length;
        int [] f=new int[amount + 1];
        f[0] = 1;
        for (int i=1;i<=len;i++){
            int val= coins[i - 1];
            for (int j=val;j<=amount;j++){
                f[j]+=f[j-val];
            }
        }
        return f[amount];
    }
}
