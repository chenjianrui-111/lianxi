package com.xiaochao.动态规划;

/**
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 */
//解法一：备忘录（自顶向下）
public class 斐波那契数 {
    int fib(int N) {
        // 备忘录全初始化为 0
        int[] memo = new int[N + 1];
        // 进⾏带备忘录的递归
        return helper(memo, N);
    }
    int helper(int[] memo, int n) {
        // base case
        if (n == 0 || n == 1) return n;
        // 已经计算过，不⽤再计算了
        if (memo[n] != 0) return memo[n];
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }
}
//解法二：dp 自底向上
class solution{
    int fib(int N){
        if (N == 0) return 0;
        int[] dp =new int[N+1];
        //BASE CASE
        dp[0] = 0;dp[1] = 1;
        //状态转移
        for (int i = 2; i <= N ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }
}

/**
 *根据斐波那契数列的状态转移⽅程，当前状态只和之前的两个状态有关，其实并不需要 那么⻓的⼀个 DP table 来存储所有的状态，
 * 只要想办法存储之前的两个状态就⾏了。
 */
class solution2{
    int fib(int n) {
        if (n == 0 || n == 1) {
            // base case
            return n;
        }
        // 分别代表 dp[i - 1] 和 dp[i - 2]
        int dp_i_1 = 1, dp_i_2 = 0;
        for (int i = 2; i <= n; i++) {
            // dp[i] = dp[i - 1] + dp[i - 2];
            int dp_i = dp_i_1 + dp_i_2;
            // 滚动更新
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i_1;
    }
}
