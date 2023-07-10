package com.xiaochao.数组链表.前缀和;

import java.util.HashMap;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 */
public class 和为K的子数组 {
    public int subarraySum(int[] nums, int k) {
        int n =nums.length;
        int[] preSum = new int[n+1];
        preSum[0] = 0;
        HashMap<Integer, Integer> valIoIndex = new HashMap<>();
        valIoIndex.put(0,1);
        // 记录和为 k 的子数组个数
        int res = 0;
        // 计算 nums 的前缀和
        for (int i = 1; i <=nums.length ; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
            // 如果之前存在值为 need 的前缀和
            // 说明存在以 nums[i-1] 结尾的子数组的和为 k
            int need =preSum[i] - k;
            if (valIoIndex.containsKey(need)){
                res+=valIoIndex.get(need);
            }
            // 将当前前缀和存入哈希表
            if (!valIoIndex.containsKey(preSum[i])){
                valIoIndex.put(preSum[i],1);
            }else {
                valIoIndex.put(preSum[i],valIoIndex.get(preSum[i])+1);
            }
        }
        return res;
    }
}
