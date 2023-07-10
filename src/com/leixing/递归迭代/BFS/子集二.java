package com.leixing.递归迭代.BFS;

import java.util.*;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 回溯解法（Set）
 * 由于是求所有的方案，而且数据范围只有 10，可以直接用爆搜来做。
 * 同时由于答案中不能包含相同的方案，因此我们可以先对原数组进行排序，
 * 从而确保所有爆搜出来的方案，都具有单调性，然后配合 Set 进行去重。
 */
public class 子集二 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> ans=new HashSet<>();
        List<Integer> cur=new ArrayList<>();
        dfs(nums,0,cur,ans);
        return new ArrayList<>(ans);
    }

    /**
     * @param nums 原输入数组
     * @param u 当前决策到原输入数组中的哪一位
     * @param cur 当前方案
     * @param ans 最终结果集
     */
    void dfs(int[] nums, int u, List<Integer> cur, Set<List<Integer>> ans) {
        // 所有位置都决策完成，将当前方案放入结果集
        if (nums.length == u) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        // 选择当前位置的元素，往下决策
        cur.add(nums[u]);
        dfs(nums, u + 1, cur, ans);

        // 不选当前位置的元素（回溯），往下决策
        cur.remove(cur.size() - 1);
        dfs(nums, u + 1, cur, ans);
    }
}
/**
 *时间复杂度：排序复杂度为 O(nlogn)，爆搜复杂度为 (2^n)每个方案通过深拷贝存入答案，复杂度为 O(n)。整体复杂度为 (n * 2^n)
 * 空间复杂度：总共有 2^n个方案，每个方案最多占用 O(n) 空间，整体复杂度为 (n * 2^n)
 */
