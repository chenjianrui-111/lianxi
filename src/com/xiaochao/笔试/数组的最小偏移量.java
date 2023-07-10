package com.xiaochao.笔试;

import java.util.TreeSet;

/**
 * 给你一个由 n 个正整数组成的数组 nums 。
 * 你可以对数组的任意元素执行任意次数的两类操作：
 * 如果元素是 偶数 ，除以 2
 * 例如，如果数组是 [1,2,3,4] ，那么你可以对最后一个元素执行此操作，使其变成 [1,2,3,2]
 * 如果元素是 奇数 ，乘上 2
 * 例如，如果数组是 [1,2,3,4] ，那么你可以对第一个元素执行此操作，使其变成 [2,2,3,4]
 * 数组的 偏移量 是数组中任意两个元素之间的 最大差值 。
 * 返回数组在执行某些操作之后可以拥有的 最小偏移量 。
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：1
 * 解释：你可以将数组转换为 [1,2,3,2]，然后转换成 [2,2,3,2]，偏移量是 3 - 2 = 1
 * 解法
 * 首先: 一个数的变化范围有限, 比如所有的奇数都只能做一次乘 2 操作, 偶数可以做若干次除以 2 的操作.
 * 比如 3 可以变成 3, 6; 4 可以变成 1, 2, 4; 12 可以变成 3, 6, 12...
 * 数值可以双向变化不好处理, 我们先变成单向的: 把所有数都变成自己可变化范围的最大值.
 * 那么现在剩下的操作就只有把数缩小了.
 * 而偏移量 = 最大值 - 最小值, 所以我们要做的就是缩小最大值. (缩小其他数值也无法优化偏移量)
 * 那么操作就是: 不断缩小当前的最大值即可, 直到不能缩小, 期间不断维护答案.
 * Java 使用 TreeSet 或者 PriorityQueue 都可以动态维护最大值.
 * 空间复杂度 o(N), 时间复杂度 o(NlogNlogN)
 * 最多循环 NlogN 次, 因为一个数除以 2 的次数是 logN; 每次循环是 logN.
 */
public class 数组的最小偏移量 {
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num % 2 == 0 ? num : num * 2);
        }
        int res = set.last() - set.first();
        while (res > 0 && set.last() % 2 == 0) {
            int max = set.last();
            set.remove(max);
            set.add(max / 2);
            res = Math.min(res, set.last() - set.first());
        }
        return res;
    }

}

