package com.xiaochao.回溯算法;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 元素可重不可复选
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 */
public class 子集二 {

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序，让相同的元素靠在⼀起
        Arrays.sort(nums);
        backtrack(nums, 0);
        return res;
    }
    void backtrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是⼀个⼦集
        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            // 剪枝逻辑，值相同的相邻树枝，只遍历第⼀条
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            track.addLast(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }
}
