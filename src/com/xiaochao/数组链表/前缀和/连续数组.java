package com.xiaochao.数组链表.前缀和;

import java.util.HashMap;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 */
public class 连续数组 {
    public int findMaxLength(int[] nums) {
        int n =nums.length;
        int[] preSum=new int[n + 1];
        preSum[0] = 0;
        //计算nums的前缀和
        for (int i = 0
             ; i <n ; i++) {
            preSum[i+1] = preSum[i] +(nums[i] == 0 ? -1 : 1);
        }
        // 前缀和到索引的映射，方便快速查找所需的前缀和
        HashMap<Integer,Integer> hashMap =new HashMap<>();
        int res =0;
        for (int i = 0; i <preSum.length ; i++) {
            // 如果这个前缀和还没有对应的索引，说明这个前缀和第一次出现，记录下来
            if (!hashMap.containsKey(preSum[i])){
                hashMap.put(preSum[i],i);
            }else {
                // 这个前缀和已经出现过了，则找到一个和为 0 的子数组
                res = Math.max(res, i - hashMap.get(preSum[i]));
            }
        }
        return res;
    }
}
