package com.leixing.动态规划.记忆化搜索;

/**
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 *
 * DFS
 * 数据范围只有 2020，而且每个数据只有 +/-+/− 两种选择，因此可以直接使用 DFS 进行「爆搜」。
 * 而 DFS 有「使用全局变量维护」和「接收返回值处理」两种形式。
 */
public class 目标和 {
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums,target,0,0);
    }
    int dfs(int []nums,int target,int u, int cur){
        if (u==nums.length){
            return cur == target ? 1 : 0;
        }
        int left=dfs(nums,target,u + 1,cur + nums[u]);
        int right=dfs(nums,target,u + 1,cur - nums[u]);
        return left + right;
    }
}
//时间复杂度：O(2^n)
//空间复杂度：忽略递归带来的额外空间消耗。复杂度为 O(1)
