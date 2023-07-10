package com.xiaochao.前缀和;

/**
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 * 示例 1：
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * 示例 2：
 * 输入:nums = [1,2,3], k = 3
 * 输出: 2
 * 提示:
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀和 + 哈希表
 * 这是一道经典的前缀和运用题。
 * 统计以每一个 nums[i] 为结尾，和为 k 的子数组数量即是答案。
 * 我们可以预处理前缀和数组 sum（前缀和数组下标默认从 1 开始），对于求解以某一个 nums[i] 为结尾的，和为 k 的子数组数量，
 * 本质上是求解在 [0,i] 中，sum 数组中有多少个值为 sum[i+1]-k 的数，这可以在遍历过程中使用「哈希表」进行同步记录。
 */
public class 和为k的子数组 {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length,ans = 0;
        int[] sums = new int[n+10];
        for (int i = 1; i <= n ; i++) {
            sums[i] = sums[i-1] + nums[i-1];
        }
        Map<Integer,Integer> map =new HashMap<>();
        map.put(0,1);
        for (int i = 1; i <= n ; i++) {
            int t = sums[i],d = t -k ;
            ans += map.getOrDefault(d,0);
            map.put(t,map.getOrDefault(t,0) + 1);
        }
        return ans;
     }
}
