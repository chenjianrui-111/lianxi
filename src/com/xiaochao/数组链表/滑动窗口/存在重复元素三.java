package com.xiaochao.数组链表.滑动窗口;

import java.util.TreeSet;

/**
 * 给你一个整数数组 nums 和两个整数 k 和 t。请你判断是否存在两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t，
 * 同时又满足 abs(i - j) <= k。如果存在则返回 true，不存在返回 false。
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 */
public class 存在重复元素三 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> window = new TreeSet<>();
        int left = 0, right = 0;
        while (right < nums.length) {
            // 为了防止 i == j，所以在扩大窗口之前先判断是否有符合题意的索引对 (i, j)
            // 查找略大于 nums[right] 的那个元素
            Integer ceiling = window.ceiling(nums[right]);
            if (ceiling != null && (long) ceiling - nums[right] <= t) {
                return true;
            }
            // 查找略小于 nums[right] 的那个元素
            Integer floor = window.floor(nums[right]);
            if (floor != null && (long) nums[right] - floor <= t) {
                return true;
            }

            // 扩大窗口
            window.add(nums[right]);
            right++;

            if (right - left > k) {
                // 缩小窗口
                window.remove(nums[left]);
                left++;
            }
        }
        return false;
    }
}
