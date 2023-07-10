package com.xiaochao.动态规划;

public class 最大子数组和 {
    public int maxSubArray(int[] nums) {
        int n =nums.length;
        if (n == 0) return 0;
        //以 nums[i] 为结尾的「最大子数组和」为 dp[i]
        int[] dp = new int[n];
        dp[0] = nums[0];
        //状态转移方程
        for (int i = 1; i < n ; i++) {
            //有两个选择，要么与前面的相邻子数组连接，形成一个和更大的子数组；要么不与前面的子数组连接，自成一派，自己作为一个子数组。
            dp[i] = Math.max(nums[i],nums[i] + dp[i - 1]);
        }
        //得到 nums 的最大子数组
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n ; i++) {
            res = Math.max(res,dp[i]);
        }
        return res;
    }
    int maxSubArray2(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        // base case
        int dp_0 = nums[0];
        int dp_1 = 0, res = dp_0;
        for (int i = 1; i < n; i++) {
            // dp[i] = max(nums[i], nums[i] + dp[i-1])
            dp_1 = Math.max(nums[i], nums[i] + dp_0);
            dp_0 = dp_1;
            // 顺便计算最⼤的结果
            res = Math.max(res, dp_1);
        }

        return res;
    }
    // 前缀和技巧解题
    int maxSubArray3(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        // 构造 nums 的前缀和数组
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int res = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 维护 minVal 是 preSum[0..i] 的最⼩值
            minVal = Math.min(minVal, preSum[i]);
            // 以 nums[i] 结尾的最⼤⼦数组和就是 preSum[i+1] - min(preSum[0..i])
            res = Math.max(res, preSum[i + 1] - minVal);
        }
        return res;
    }
}
