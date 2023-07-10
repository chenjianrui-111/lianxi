package com.xiaochao.动态规划;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。 
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 */
public class 零钱兑换 {
    int coinChange(int[] coins, int amount) {
        // 题⽬要求的最终结果是 dp(amount)
        return dp(coins, amount);
    }
    // 定义：要凑出⾦额 n，⾄少要 dp(coins, n) 个硬币
    int dp(int[] coins, int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 计算⼦问题的结果
            int subProblem = dp(coins, amount - coin);
            // ⼦问题⽆解则跳过
            if (subProblem == -1) continue;
            // 在⼦问题中选择最优解，然后加⼀
            res = Math.min(res, subProblem + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
class soution{
    int[] memo;
    int coinChange(int[] coins,int amount){
        memo = new int[amount + 1];
        Arrays.fill(memo,-666);
        return dp(coins,amount);
    }
    int dp(int [] coins,int amount){
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (memo[amount] != -666){
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin :coins){
            //计算子问题的计算结果
            int subProblem = dp(coins, amount - coin);
            // ⼦问题⽆解则跳过
            if (subProblem == -1) continue;
            // 在⼦问题中选择最优解，然后加⼀
            res = Math.min(res, subProblem + 1);
        }
        //把计算结果放入备忘录
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }
}
class solution3{
    int coinChange(int[] coins,int amount){
        //dp 数组的定义：当⽬标⾦额为 i 时，⾄少需要 dp[i] 枚硬币凑出。
        //凑成 amount ⾦额的硬币数最多只可能等 于 amount（全⽤ 1 元⾯值的硬币），
        // 所以初始化为 amount + 1 就相当于初始化为正⽆穷，便于后 续取最⼩值。
        int [] dp =new int[amount + 1];
        Arrays.fill(dp,amount+1);
        //base case
        dp[0] = 0;

        for (int i = 0; i < dp.length; i++) {
            for (int coin:coins){
                if (i -coin < 0){
                    continue;
                }
                dp[i] = Math.min(dp[i],dp[i - coin] + 1);
            }
        }
        return (dp[amount] == amount + 1 ) ? -1 :dp[amount];
    }
}
