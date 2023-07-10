package com.xiaochao.数组链表.滑动窗口;

/**
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改
 * 数组以供接下来的操作使用。
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 * 示例 1：
 * 输入：nums = [1,1,4,2,3], x = 5
 * 输出：2
 * 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 */
public class 将x减到0的最小操作数 {
    public int minOperations(int[] nums, int x) {
        int n =nums.length,sum = 0;
        for (int i = 0; i < n ; i++) {
            sum += nums[i];
        }
        //滑动窗口需要寻找的子数组目标和
        int target = sum - x;
        int left = 0,right = 0;
        //记录窗口内所有元素之和
        int windowSum = 0;
        //记录目标子数组的最大长度
        int maxLen = Integer.MIN_VALUE;
        //开始执行滑动窗口框架
        while (right < nums.length){
            //扩大窗口
            windowSum += nums[right];
            right++;
            while (windowSum > target && left < right){
                //缩小窗口
                windowSum -= nums[left];
                left++;
            }
            //寻找目标子数组
            if (windowSum == target){
                maxLen =Math.max(maxLen,right - left);
            }
        }
        //目标子数组的最大长度可以推导出需要删除的字符数量
        return maxLen == Integer.MIN_VALUE ? -1 : n - maxLen;
    }
}
