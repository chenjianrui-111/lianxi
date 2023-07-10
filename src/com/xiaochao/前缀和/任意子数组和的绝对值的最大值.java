package com.xiaochao.前缀和;

/**
 * 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 * abs(x) 定义如下：
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 * 示例 1：
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 * 示例 2：
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class 任意子数组和的绝对值的最大值 {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int[] sum  =new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int ans = 0;
        for (int i = 1,min = 0,max = 0; i <= n ; i++) {
            ans = Math.max(ans,Math.abs(sum[i] - min));
            ans = Math.max(ans,Math.abs(sum[i] - max));
            max = Math.max(max,sum[i]);
            min = Math.min(min,sum[i]);
        }
        return ans;
    }
}
//• 时间复杂度：O(n) • 空间复杂度：O(n)
