package com.xiaochao.数组链表.滑动窗口;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 * 示例 1：
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 */
public class 乘积小于K的子数组 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left =0 ,right = 0;
        //滑动窗口，初始化为乘法单位元
        int windowProduct = 1;
        // 记录符合条件的子数组（窗口）个数
        int count = 0;

        while (right < nums.length){
            //扩大窗口
            windowProduct = windowProduct * nums[right];
            right++;
            while (left < right && windowProduct >= k){
                //减小窗口
                windowProduct = windowProduct / nums[left];
                left++;
            }
            count += right - left;
        }
        return count;
    }
}
