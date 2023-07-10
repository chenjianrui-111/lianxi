package com.xiaochao.动态规划;

/**
 *有⼀个背包，最⼤容量为 amount，有⼀系列物品 coins，每个物品的重量为 coins[i]，每个物品的数量 ⽆限。请问有多少种⽅法，
 * 能够把背包恰好装满？ 第⼀步要明确两点，「状态」和「选择」，状态有两个，就是「背包的容量」和「可选择的物品」，选择就 是「装进背包」或者「不装进背包」。
 * dp[i][j] 的定义：若只使⽤前 i 个物品（可以重复使⽤），当背包容量为 j 时，有 dp[i][j] 种⽅法可以 装满背包。
 * 最终想得到的答案是 dp[N][amount]，其中 N 为 coins 数组的⼤⼩。 如果你不把这第 i 个物品装⼊背包，
 * 也就是说你不使⽤ coins[i] 这个⾯值的硬币，那么凑出⾯额 j 的⽅法 数 dp[i][j] 应该等于 dp[i-1][j]，继承之前的结果。 如果你把这第 i 个物品装⼊了背包，也就是说你使⽤ coins[i] 这个⾯值的硬币，那么 dp[i][j] 应该等于
 * dp[i][j-coins[i-1]]。
 */
public class 零钱兑换二 {
    public int change(int amount, int[] coins) {
        int n =coins.length;
        int[][] dp= new int[n+1][amount+1];
        //base case
        for (int i = 0; i < n ; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= amount ; j++) {
                if (j - coins[i-1] >= 0){
                    dp[i][j] = dp[i-1][j] +dp[i][j-coins[i-1]];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][amount];
    }
}
