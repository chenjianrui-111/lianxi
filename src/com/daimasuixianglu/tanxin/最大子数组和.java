package com.daimasuixianglu.tanxin;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 */
public class 最大子数组和 {
    //贪心
    public int maxSubArray(int[] nums) {
        if (nums.length ==1){
            return nums[0];
        }
        int sum=Integer.MIN_VALUE;
        int count=0;
        for (int i = 0; i <nums.length ; i++) {
            count+=nums[i];
            sum=Math.max(sum,count);
            if (count<=0){
                count=0;
            }
        }
        return sum;
    }
}
// DP 方法
class Solution01 {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        ans = dp[0];

        for (int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            ans = Math.max(dp[i], ans);
        }

        return ans;
    }
}
