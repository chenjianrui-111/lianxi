package com.leixing.递归迭代.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 *给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都 互不相同
 * 1 <= target <= 500
 *
 * DFS + 回溯解法
 * 这道题很明显就是在考察回溯算法。
 * 还记得三叶之前跟你分享过的 37. 解数独（困难） 吗？
 * 里面有提到我们应该如何快速判断一道题是否应该使用 DFS + 回溯算法来爆搜。
 * 总的来说，你可以从两个方面来考虑：
 * 1. 求的是所有的方案，而不是方案数。 由于求的是所有方案，不可能有什么特别的优化，我们只能进行枚举。这时候可能的解法有动态规划、记忆化搜索、DFS + 回溯算法。
 * 2. 通常数据范围不会太大，只有几十。 如果是动态规划或是记忆化搜索的题的话，由于它们的特点在于低重复/不重复枚举，所以一般数据范围可以出到 10^5
 *   到 10^7
 * 而 DFS + 回溯的话，通常会限制在 30 以内。
 * 这道题数据范围是 30 以内，而且是求所有方案，因此我们使用 DFS + 回溯来求解。
 */
public class 组合总和 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
       List<Integer> cur=new ArrayList<>();
       List<List<Integer>> ans=new ArrayList<>();
       dfs(candidates,target,0,ans,cur);
       return ans;
    }
   /** cs: 原数组，从该数组进行选数
     * t: 还剩多少值需要凑成。起始值为 target ，代表还没选择任何数；当 t = 0，代表选择的数凑成了 target
     * u: 当前决策到 cs[] 中的第几位
     * ans: 最终结果集
     * cur: 当前结果集
     */

   void dfs(int [] cs,int t,int u, List<List<Integer>> ans,List<Integer> cur){
       if (t == 0){
           ans.add(new ArrayList<>(cur));
           return;
       }
       if (u == cs.length || t < 0) {
           return;
       }

       // 枚举 cs[u] 的使用次数
       for (int i=0; cs[u] * i <=t;i++){
           dfs(cs,t - cs[u] * i,u+1,ans,cur);
           cur.add(cs[u]);
       }
       // 进行回溯。注意回溯总是将数组的最后一位弹出
       for (int i = 0; cs[u] * i <= t; i++) {
           cur.remove(cur.size() - 1);
       }
   }
}
/**
 *时间复杂度：由于每个数字的使用次数不确定，因此无法分析具体的复杂度。但是 DFS 回溯算法通常是指数级别的复杂度
 * （因此数据范围通常为 30 以内）。这里暂定 O(n * 2^n)
 * 空间复杂度：同上。复杂度为 O(n * 2^n)
 */
