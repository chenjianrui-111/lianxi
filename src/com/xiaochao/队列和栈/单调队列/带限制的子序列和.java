package com.xiaochao.队列和栈.单调队列;

import java.util.LinkedList;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回 非空 子序列元素和的最大值，子序列需要满足：
 * 子序列中每两个 相邻 的整数 nums[i] 和 nums[j] ，它们在原数组中的下标 i 和 j 满足 i < j 且 j - i <= k 。
 * 数组的子序列定义为：将数组中的若干个数字删除（可以删除 0 个数字），剩下的数字按照原本的顺序排布。
 * 示例 1：
 * 输入：nums = [10,2,-10,5,20], k = 2
 * 输出：37
 * 解释：子序列为 [10, 2, 5, 20] 。
 */
public class 带限制的子序列和 {
       public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        // 定义：dp[i] 表示以 nums[i] 结尾的子序列的最大和
        int[] dp = new int[n];
        dp[0] = nums[0];
        // 单调队列辅助计算 dp[i-k..i-1] 的最大值
        MonotonicQueue5<Integer> window = new MonotonicQueue5<>();
        window.push(dp[0]);

        for (int i = 1; i < n; i++) {
            // 状态转移方程
            dp[i] = Math.max(nums[i], window.max() + nums[i]);
            // 维护滑动窗口的大小为 k
            if (window.size() == k) {
                window.pop();
            }
            window.push(dp[i]);
        }
        // dp 数组中的最大值就是结果
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
class MonotonicQueue5<E extends Comparable<E>> {
    // 常规队列，存储所有元素
    LinkedList<E> q = new LinkedList<>();
    // 元素降序排列的单调队列，头部是最大值
    LinkedList<E> maxq = new LinkedList<>();
    // 元素升序排列的单调队列，头部是最小值
    LinkedList<E> minq = new LinkedList<>();

    public void push(E elem) {
        // 维护常规队列，直接在队尾插入元素
        q.addLast(elem);

        // 维护 maxq，将小于 elem 的元素全部删除
        while (!maxq.isEmpty() && maxq.getLast().compareTo(elem) < 0) {
            maxq.pollLast();
        }
        maxq.addLast(elem);

        // 维护 minq，将大于 elem 的元素全部删除
        while (!minq.isEmpty() && minq.getLast().compareTo(elem) > 0) {
            minq.pollLast();
        }
        minq.addLast(elem);
    }

    public E max() {
        // maxq 的头部是最大元素
        return maxq.getFirst();
    }

    public E min() {
        // minq 的头部是最大元素
        return minq.getFirst();
    }

    public E pop() {
        // 从标准队列头部弹出需要删除的元素
        E deleteVal = q.pollFirst();
        assert deleteVal != null;

        // 由于 push 的时候会删除元素，deleteVal 可能已经被删掉了
        if (deleteVal.equals(maxq.getFirst())) {
            maxq.pollFirst();
        }
        if (deleteVal.equals(minq.getFirst())) {
            minq.pollFirst();
        }
        return deleteVal;
    }

    public int size() {
        // 标准队列的大小即是当前队列的大小
        return q.size();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }
}
// 未经优化的动态规划解法，超时
class Solution2 {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        // 定义：dp[i] 表示以 nums[i] 结尾的子序列的最大和
        int[] dp = new int[n];
        // base case，以 nums[0] 结尾的子序列只有它本身
        dp[0] = nums[0];

        // 状态转移方程
        for (int i = 1; i < n; i++) {
            int maxVal = 0;
            for (int j = 1; j <= k; j++) {
                if (i - j < 0) {
                    continue;
                }
                maxVal = Math.max(maxVal, dp[i - j]);
            }
            // dp[i] 的值可以根据 dp[i-k..i-1] 的最大值推导出来
            dp[i] = maxVal + nums[i];
        }

        // dp 数组中的最大值就是结果
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
