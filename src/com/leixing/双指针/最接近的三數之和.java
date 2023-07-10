package com.leixing.双指针;

import java.util.Arrays;

/**
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，
 * 使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 * 示例 1：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 示例 2：
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 * 提示：
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 *
 * 解題思路：
 * 排序 + 双指针
 * 这道题的思路和「15. 三数之和（中等）」区别不大。
 * 对数组进行排序，使用三个指针 i、j 和 k 分别代表要找的三个数。
 * 通过枚举 i 确定第一个数，另外两个指针 j，k 分别从左边 i + 1 和右边 n - 1 往中间移动，找到满足 nums[i] + nums[j] + nums[k] 最接近 target 的唯一解。
 * j 和 k 指针的移动逻辑，分情况讨论 sum = nums[i] + nums[j] + nums[k] ：
 * sum > target：k 左移，使 sum 变小
 * sum < target：j 右移，使 sum 变大
 * sum = target：找到最符合要求的答案，直接返回
 * 为了更快找到答案，对于相同的 i，可以直接跳过下标。
 */
public class 最接近的三數之和 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans=nums[0]+nums[1]+nums[2];
        int n=nums.length;
        for (int i=0;i<n;i++){
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int j= i + 1,k=n - 1;
            while (j < k){
                int sum=nums[i]+nums[j]+nums[k];
                if (Math.abs(sum -target) < Math.abs(ans - target)) ans=sum;
                if (ans == target){
                    return  target;
                }else if (sum > target){
                    k--;
                }else if (sum < target){
                    j++;
                }

            }
        }
        return ans;
    }
}
