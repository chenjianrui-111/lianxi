package com.xiaochao.回溯算法;

import java.util.HashMap;

/**
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 示例 1：
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 */
public class 划分成k个相等的子集 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        if (k > n) return false;
        int sum = 0;
        for (int v : nums) {
            sum += v;
        }
        if (sum % k != 0) return false;
        //使用位图技巧
        int used = 0;
        int target = sum / k;
        // k 号桶初始什么都没装，从 nums[0] 开始做选择
        return backtrack(k, 0, nums, 0, used, target);
    }

    HashMap<Integer, Boolean> memo = new HashMap<>();

    //bucket 为桶中元素之和
    private boolean backtrack(int k, int bucket, int[] nums, int start, int used, int target) {
        //base case
        if (k == 0) {
            //所有桶都被装满了
            return true;
        }
        if (bucket == target) {
            // 装满了当前桶，递归穷举下一个桶的选择
            // 让下一个桶从 nums[0] 开始选数字
            boolean res = backtrack(k - 1, 0, nums, 0, used, target);
            //缓存结果
            memo.put(used, res);
            return res;
        }
        if (memo.containsKey(used)) {
            // 避免冗余计算
            return memo.get(used);
        }
        for (int i = start; i < nums.length; i++) {
            //剪枝
            if (((used >> i) & 1) == 1) {
                //判断第 i 位是否为 1
                // nums[i] 已经被装入别的桶中
                continue;
            }
            if (nums[i] + bucket > target) {
                continue;
            }
            //做选择
            used |= 1 << i;//将第 i 位置为 1
            bucket += nums[i];


            // 递归穷举下一个数字是否装入当前桶
            if (backtrack(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            // 撤销选择
            used ^= 1 << i; // 将第 i 位置为 0
            bucket -= nums[i];
        }

        return false;
    }

}
