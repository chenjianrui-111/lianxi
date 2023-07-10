package com.xiaochao.贪心;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都 互不相同
 * 1 <= target <= 500
 */
public class 组合总和 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(candidates,target,0,ans,cur);
        return ans;
    }
    /**
     * cs: 原数组，从该数组进行选数
     * t: 还剩多少值需要凑成。起始值为 target ，代表还没选择任何数；当 t = 0，代表选择的数凑成了 target
     * u: 当前决策到 cs[] 中的第几位
     * ans: 最终结果集
     * cur: 当前结果集
     */
    void dfs(int[] cs, int t, int u, List<List<Integer>> ans, List<Integer> cur) {
        if (t == 0){
            ans.add(new ArrayList<>(cur));
            return;
        }
        //枚举 cs[u] 的使用次数
        for (int i = 0; cs[u] * i <= t ; i++) {
            dfs(cs,t - cs[u] * i,u + 1,ans,cur);
            cur.add(cs[u]);
        }
        //进行回溯，回溯总是将数组的最后一位弹出
        for (int i = 0; cs[u] * i <= t ; i++) {
            cur.remove(cur.size() - 1);
        }
    }
}
//• 时间复杂度：由于每个数字的使用次数不确定，因此无法分析具体的复杂度。但是
//DFS 回溯算法通常是指数级别的复杂度（因此数据范围通常为 30 以内）。这里暂 定 O(n ∗ 2n) • 空间复杂度：同上。复杂度为 O(n ∗ 2n)
