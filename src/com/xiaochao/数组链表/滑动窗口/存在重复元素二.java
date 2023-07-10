package com.xiaochao.数组链表.滑动窗口;

import java.util.HashSet;

/**
 * 给你一个整数数组 nums 和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，满足 nums[i] == nums[j] 且 abs(i - j) <= k。
 * 如果存在，返回 true；否则，返回 false。
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 */
public class 存在重复元素二 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int left = 0 ,right = 0;
        HashSet<Integer> window =new HashSet<>();
        // 滑动窗口算法框架，维护一个大小为 k 的窗口
        while (right < nums.length){
            //扩大窗口
            if (window.contains(nums[right])){
                return true;
            }
            window.add(nums[right]);
            right++;

            if (right - left > k) {
                // 当窗口的大小大于 k 时，缩小窗口
                window.remove(nums[left]);
                left++;
            }
        }
        return false;
    }
}
