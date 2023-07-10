package com.leixing.动态规划.记忆化搜索;

/**
 * 递归实现动态规划
 * 能以「递推」形式实现动态规划，自然也能以「递归」的形式实现。
 * 为防止重复计算，我们需要加入「记忆化搜索」功能，同时利用某个值 xx 在不同的样例之间可能
 * 会作为“中间结果”被重复计算，并且计算结果 fib(x)fib(x) 固定，我们可以使用 static 修饰缓存器，
 * 以实现计算过的结果在所有测试样例中共享。
 */
public class 剑指Offer10斐波那契数列2 {
    static int mod = (int)1e9+7;
    static int N = 110;
    static int[] cache = new int[N];
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        if (cache[n] != 0) {
            return cache[n];
        }
        cache[n] = fib(n - 1) + fib(n - 2);
        cache[n] %= mod;
        return cache[n];
    }
}
//时间复杂度：O(n)
//空间复杂度：O(1)
