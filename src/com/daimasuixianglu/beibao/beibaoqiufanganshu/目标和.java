package com.daimasuixianglu.beibao.beibaoqiufanganshu;

/**
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 */

import java.util.HashMap;
import java.util.Map;

/**
 DFS
 数据范围只有 20，而且每个数据只有 +/- 两种选择，因此可以直接使用 DFS 进行「爆搜」。
 而 DFS 有「使用全局变量维护」和「接收返回值处理」两种形式。
 */
public class 目标和 {
    public int findTargetSumWays(int[] nums, int t) {
        return dfs(nums,t,0,0);
    }
    int dfs(int[]nums,int t,int u,int cur){
        if (u==nums.length){
            return cur == t ? 1 : 0;
        }
        int left=dfs(nums, t, u+1, cur+nums[u]);
        int right=dfs(nums, t, u+1, cur-nums[u]);
        return left + right;
    }
}
//时间复杂度：O(2^N)
//空间复杂度：忽略递归带来的额外空间消耗。复杂度为O(1)

/**
 *记忆化搜索
 * 不难发现，在 DFS 的函数签名中只有「数值下标 u」和「当前结算结果 cur」为可变参数，考虑将其作为记忆化容器的两个维度，返回值作为记忆化容器的记录值。
 * 由于 cur 存在负权值，为了方便，我们这里不设计成静态数组，而是使用「哈希表」进行记录。
 */
class  Solution01{
    public int findTargetSumWays(int[] nums,int target){
        return dfs(nums,target,0,0);
    }
    Map<String,Integer> map=new HashMap<>();
    int dfs(int[] nums,int target,int u,int cur){
        String key=u +"_"+cur;
        if (map.containsKey(key)) return map.get(key);
        if (u ==nums.length){
            map.put(key,cur == target ? 1:0);
            return map.get(key);
        }
        int left=dfs(nums,target,u+1,cur+nums[u]);
        int right=dfs(nums,target,u+1,cur-nums[u]);
        map.put(key,left+right);
        return map.get(key);
    }
}
/**
 *
 动态规划
 能够以「递归」的形式实现动态规划（记忆化搜索），自然也能使用「递推」的方式进行实现。
 根据记忆化搜索的分析，我们可以定义：
f[i][j] 代表考虑前 i 个数，当前计算结果为 j 的方案数，令 nums 下标从 1 开始。
 那么 f[n][target] 为最终答案，f[0][0]=1 为初始条件：代表不考虑任何数，凑出计算结果为 0 的方案数为 1 种。
 根据每个数值只能搭配 +/- 使用，可得状态转移方程： f[i][j]=f[i-1][j-nums[i-1]]+f[i-1][j+nums[i-1]]
 到这里，既有了「状态定义」和「转移方程」，又有了可以滚动下去的「有效值」（起始条件）。
 距离我们完成所有分析还差最后一步。
 当使用递推形式时，我们通常会使用「静态数组」来存储动规值，因此还需要考虑维度范围的：
 第一维为物品数量：范围为 nums 数组长度
 第二维为中间结果：令 s 为所有 nums 元素的总和（题目给定了 nums[i] 为非负数的条件，否则需要对 nums[i] 取绝对值再累加），那么中间结果的范围为
 因此，我们可以确定动规数组的大小。同时在转移时，对第二维度的使用做一个 s 的右偏移，以确保「负权值」也能够被合理计算/存储。
 */
class Solution
{
    public int findTargetSumWays(int[] nums, int t) {
        int n = nums.length;
        int s = 0;
        for (int i : nums) s += Math.abs(i);
        if (Math.abs(t) > s) return 0;
        int[][] f = new int[n + 1][2 * s + 1];
        f[0][0 + s] = 1;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = -s; j <= s; j++) {
                if ((j - x) + s >= 0) f[i][j + s] += f[i - 1][(j - x) + s];
                if ((j + x) + s <= 2 * s) f[i][j + s] += f[i - 1][(j + x) + s];
            }
        }
        return f[n][t + s];
    }
}
