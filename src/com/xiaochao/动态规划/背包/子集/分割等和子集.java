package com.xiaochao.动态规划.背包.子集;

/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 */
public class 分割等和子集 {
    boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        // 和为奇数时，不可能划分成两个和相等的集合
        if (sum % 2 != 0) return false;
        int n = nums.length;
        sum = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // base case
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    // 背包容量不⾜，不能装⼊第 i 个物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 装⼊或不装⼊背包
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }
}
/****** 变成一维数组 ******/

/**
 * 其实这段代码和之前的解法思路完全相同，只在⼀⾏ dp 数组上操作，i 每进⾏⼀轮迭代，dp[j] 其实就相 当于 dp[i-1][j]，
 * 所以只需要⼀维数组就够⽤了。 唯⼀需要注意的是 j 应该从后往前反向遍历，因为每个物品（或者说数字）只能⽤⼀次，
 * 以免之前的结果影 响其他的结果。 ⾄此，⼦集切割的问题就完全解决了，时间复杂度 O(n\*sum)，空间复杂度 O(sum)。
 */
class Solution{
    boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        // 和为奇数时，不可能划分成两个和相等的集合
        if (sum % 2 != 0) return false;
        int n = nums.length;
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];

        // base case
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[sum];
    }
}
