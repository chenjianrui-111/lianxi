package com.xiaochao.数组链表.前缀和;

import java.util.HashMap;

public class 和可被K整除的子数组 {
    public int subarraysDivByK(int[] nums, int k) {
        int n =nums.length;
        int[] preSum = new int[n+1];
        preSum[0] = 0;
        HashMap<Integer, Integer> valIoIndex = new HashMap<>();
        valIoIndex.put(0,1);
        // 记录可被 k整除 的子数组个数
        int res = 0;
        for (int i = 1; i <=n ; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
            // 如果之前存在值为 need 的前缀和
            // 说明存在以 nums[i-1] 结尾的子数组的和为 k
            int need =preSum[i] % k;
            if (need < 0) {
                // Java 求模的特性，-2 % 3 = -2，但我们实际想要正余数 1
                need += k;
            }
            if (valIoIndex.containsKey(need)){
                // 如果存在，则说明找到了可以整除 k 的子数组，累加子数组数量
                int count = valIoIndex.get(need);
                res+=valIoIndex.get(need);
                valIoIndex.put(need,count+1);
            }else {
                // 如果不存在，那么 nums[0..i] 是第一个前缀和余数为 need 的子数组
                valIoIndex.put(need,1);
            }
        }
        return res;
    }
}
