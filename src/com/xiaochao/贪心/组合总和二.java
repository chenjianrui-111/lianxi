package com.xiaochao.贪心;

import java.util.*;

/**
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。 
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * 提示:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
/**
 * 我们可以接着 39. 组合总和（中等） 的思路来修改：
 * 1. 由于每个数字只能使用一次，我们可以直接在 DFS 中决策某个数是用还是不用。
 * 2. 由于不允许重复答案，可以使用 set 来保存所有合法方案，最终再转为 list 进行返
 * 回。当然我们需要先对 cs 进行排序，确保得到的合法方案中数值都是从小到大的。
 * 这样 set 才能起到去重的作用。对于 [1,2,1] 和 [1,1,2] ，set 不会认为是相同的数组。
 */
public class 组合总和二 {
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        Arrays.sort(candidates);
        Set<List<Integer>> ans =new HashSet<>();
        List<Integer> cur = new ArrayList<>();
        dfs(candidates,target,ans,0,cur);
        return new ArrayList<>();
    }
    void dfs(int[] candidates,int target, Set<List<Integer>> ans,int u,List<Integer> cur){
        if (target == 0){
            ans.add(new ArrayList<>(cur));
            return;
        }
        if (u == candidates.length || target < 0) return;
        //使用 candidates[u]
        cur.add(candidates[u]);
        dfs(candidates,target - candidates[u],ans,u + 1,cur);
        //进行回溯
        cur.remove(cur.size() - 1);
        //不使用 candidates[u]
        dfs(candidates, target, ans, u + 1, cur);
    }
}
