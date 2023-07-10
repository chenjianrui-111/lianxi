package com.leixing.滑动窗口;

/**
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 * 示例 1：
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * 示例 2：
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 * 提示：
 * n == nums.length
 * 1 <= k <= n <= 105
 * -104 <= nums[i] <= 104
 *
 * 滑动窗口
 * 相信经历了昨天的 「动态求中位数」 的题目之后，对于今天这道滑动窗口裸题，从读题到 AC 应该是
 * 一气呵成 ~
 * 以下代码，可以作为滑动窗口模板使用：
 * 初始化将滑动窗口压满，取得第一个滑动窗口的目标值
 * 继续滑动窗口，每往前滑动一次，需要删除一个和添加一个元素
 */
public class 子数组最大平均数1 {
    public double findMaxAverage(int[] nums, int k) {
       double ans=0,sum=0;
       for (int i=0;i<k;i++){
           sum+=nums[i];
       }
       ans=sum/k;
        for (int i = k; i <nums.length ; i++) {
            sum=sum+nums[i] -nums[i-k];
            ans=Math.max(ans,sum/k);
        }
        return ans;
    }
}
/**
 *时间复杂度：每个元素最多滑入和滑出窗口一次。复杂度为 O(n)
 * 空间复杂度：O(1)
 */
