package com.leixing.动态规划.记忆化搜索;

/**
 *泰波那契序列 Tn 定义如下： 
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 * 递归实现动态规划
 * 也就是记忆化搜索，创建一个 cache 数组用于防止重复计算。
 */
public class 第N个泰波那契数 {
    static int[] cache = new int[40];
    public int tribonacci(int n) {
        if (n == 0){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        if (cache[n] != 0) {
            return cache[n];
        }
        cache[n] = tribonacci(n - 1) + tribonacci(n - 2)+tribonacci(n - 3); ;
        return cache[n];
    }
}
//时间复杂度：O(n)
//空间复杂度：O(n)
