package com.xiaochao.数组链表.前缀和;

import java.util.HashMap;

/**
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格大于「不劳累的天数」。请你返回「表现良好时间段」的最大长度。
 * 示例 1：
 * 输入：hours = [9,9,6,0,6,6,9]
 * 输出：3
 * 解释：最长的表现良好时间段是 [9,9,6]。
 */
public class 表现良好的最长时间段 {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        // 前缀和到索引的映射，方便快速查找所需的前缀和
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        int res = 0;
        for (int i = 1; i <= n; i++) {
            // 计算 nums[0..i-1] 的前缀和
            preSum[i] = preSum[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            // 如果这个前缀和还没有对应的索引，说明这个前缀和第一次出现，记录下来
            if (!valToIndex.containsKey(preSum[i])) {
                valToIndex.put(preSum[i], i);
            } else {
                // 因为题目想找长度最大的子数组，valToIndex 中的索引应尽可能小，
                // 所以这里什么都不做
            }

            // 现在我们想找 hours[0..i-1] 中元素和大于 0 的子数组
            // 这就要根据 preSum[i] 的正负分情况讨论了
            if (preSum[i] > 0) {
                // preSum[i] 为正，说明 hours[0..i-1] 都是「表现良好的时间段」
                res = Math.max(res, i);
            } else {
                // preSum[i] 为负，需要寻找一个 j 使得 preSum[i] - preSum[j] > 0
                // 且 j 应该尽可能小，即寻找 preSum[j] == preSum[i] - 1
                if (valToIndex.containsKey(preSum[i] - 1)) {
                    int j = valToIndex.get(preSum[i] - 1);
                    res = Math.max(res, i - j);
                }
            }
        }
        return res;
    }
}
