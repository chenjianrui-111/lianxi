package com.leixing.递归迭代.DFS;

import java.util.*;

/**
 *给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为
 *  target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。 
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 *
 * 我们可以接着 39. 组合总和（中等） 的思路来修改：
 * 由于每个数字只能使用一次，我们可以直接在 DFS 中决策某个数是用还是不用。
 * 由于不允许重复答案，可以使用 set 来保存所有合法方案，最终再转为 list 进行返回。当然我们需要先对 cs 进行排序，
 * 确保得到的合法方案中数值都是从小到大的。这样 set 才能起到去重的作用。对于 [1,2,1] 和 [1,1,2]，set 不会认为是相同的数组。
 */
public class 组合总和2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> cur = new ArrayList<>();
        dfs(candidates, target, 0, ans, cur);
        return new ArrayList<>(ans);
    }

    void dfs(int[] cs, int t, int u, Set<List<Integer>> ans, List<Integer> cur) {
        if (t == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        if (u == cs.length || t < 0) {
            return;
        }

        // 使用 cs[u]
        cur.add(cs[u]);
        dfs(cs, t - cs[u], u + 1, ans, cur);

        // 进行回溯
        cur.remove(cur.size() - 1);
        // 不使用 cs[u]
        dfs(cs, t, u + 1, ans, cur);
    }
}
/**
 *时间复杂度： DFS 回溯算法通常是指数级别的复杂度（因此数据范围通常为 30 以内）。这里暂定 O(n * 2^n)
 * 空间复杂度：同上。复杂度为 O(n * 2^n)
 */
