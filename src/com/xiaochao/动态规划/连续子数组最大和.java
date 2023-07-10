package com.xiaochao.动态规划;

public class 连续子数组最大和 {
    public int maxSubArray(int[] nums) {
        int n =nums.length;
        if (n == 0) return 0;
        int[] dp =new int[n];
        //base case
        dp[0] = nums[0];
        //状态转移方程
        for (int i = 1; i <n ; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        int res =Integer.MIN_VALUE;
        for (int i = 0; i < n ; i++) {
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
