package com.xiaochao.前缀和;

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

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀和 + 哈希表
 * 具体的，我们在预处理前缀和时，将 nums[i] 为 0 的值当做 −1 处理。
 * 从而将问题转化为：如何求得最长一段区间和为 0 的子数组。
 * 同时使用「哈希表」来记录「某个前缀和出现的最小下标」是多少。
 * 再结合「如果答案非 0，子数组长度至少为 2」的特性，我们让循环从 2 开始，并在循环开始前
 * 往「哈希表」存入哨兵，从而实现不需要处理边界问题。
 * PS. 哈希表常数还是比较大的，用数组模拟哈希表的卡常代码见 P2。
 */
public class 连续数组 {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + (nums[i - 1] == 1 ? 1 : -1);
        }
        int ans = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        for (int i = 2; i <= n ; i++) {
            if (!map.containsKey(sum[i - 2])) map.put(sum[i - 2],i - 2);
            if (map.containsKey(sum[i])) ans =Math.max(ans , i - map.get(sum[i]));
        }
        return ans;
    }
}
//• 时间复杂度：O(n) • 空间复杂度：O(n)
