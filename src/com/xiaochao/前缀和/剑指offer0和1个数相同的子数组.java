package com.xiaochao.前缀和;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 2:
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 * 提示：
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 */
/**
*前缀和 + 哈希表
 * 根据题意，我们可以轻易发现如下性质：如果答案非 0 ，那么子数组长度必然为偶数，且长度至少为  2。
 * 具体的，我们在预处理前缀和时，将 nums[i] 为 0 的值当做 -1 处理。
 * 从而将问题转化为：如何求得最长一段区间和为 0 的子数组。 同时使用「哈希表」来记录「某个前缀和出现的最小下标」是多少。
 */
public class 剑指offer0和1个数相同的子数组 {
    public int findMaxLength(int[] nums) {
        int n = nums.length,ans = 0;
        int[] sum = new int[n+10];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + (nums[i - 1] == 0 ? -1 : 1);
        }
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        for (int i = 1; i <= n ; i++) {
            int t = sum[i];
            if (map.containsKey(t)) ans = Math.max(ans,i - map.get(t));
            if (!map.containsKey(t)) map.put(t,i);
        }
        return ans;
    }
}
//O(N)
