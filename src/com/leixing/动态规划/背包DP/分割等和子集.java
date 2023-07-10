package com.leixing.动态规划.背包DP;

/**
 *给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 基本分析
 * 通常「背包问题」相关的题，都是在考察我们的「建模」能力，也就是将问题转换为「背包问题」的能力。
 * 由于本题是问我们能否将一个数组分成两个「等和」子集。
 * 问题等效于能否从数组中挑选若干个元素，使得元素总和等于所有元素总和的一半。
 * 这道题如果抽象成「背包问题」的话，应该是：
 * 我们背包容量为 target=sum/2，每个数组元素的「价值」与「成本」都是其数值大小，求我们能否装满背包。
 *
 * 转换为 01 背包
 * 由于每个数字（数组元素）只能被选一次，而且每个数字选择与否对应了「价值」和「成本」，求解的问题也与「最大价值」相关。
 * 可以使用「01 背包」的模型来做。
 * 当我们确定一个问题可以转化为「01 背包」之后，就可以直接套用「01 背包」的状态定义进行求解了。
 * 注意，我们积累 DP 模型的意义，就是在于我们可以快速得到可靠的「状态定义」。
 * 在 路径问题 中我教过你通用的 DP 技巧解法，但那是基于我们完全没见过那样的题型才去用的，而对于一些我们见过题型的 DP 题目，我们应该直接套用（或微调）该模型「状态定义」来做。
 * 我们直接套用「01 背包」的状态定义：
 * f[i][j] 代表考虑前 ii 个数值，其选择数字总和不超过 j 的最大价值。
 * 当有了「状态定义」之后，结合我们的「最后一步分析法」，每个数字都有「选」和「不选」两种选择。
 * 因此不难得出状态转移方程
 *
 */
public class 分割等和子集 {
    public boolean canPartition(int[] nums) {
        int n=nums.length;

        //「等和子集」的和必然是总和的一半
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        int target = sum / 2;

        // 对应了总和为奇数的情况，注定不能被分为两个「等和子集」
        if (target * 2 != sum) {
            return false;
        }

        int[][] f = new int[n][target + 1];
        // 先处理考虑第 1 件物品的情况
        for (int j = 0; j <= target; j++) {
            f[0][j] = j >= nums[0] ? nums[0] : 0;
        }

        // 再处理考虑其余物品的情况
        for (int i = 1; i < n; i++) {
            int t = nums[i];
            for (int j = 0; j <= target; j++) {
                // 不选第 i 件物品
                int no = f[i-1][j];
                // 选第 i 件物品
                int yes = j >= t ? f[i-1][j-t] + t : 0;
                f[i][j] = Math.max(no, yes);
            }
        }
        // 如果最大价值等于 target，说明可以拆分成两个「等和子集」
        return f[n-1][target] == target;

    }
}
//时间复杂度：target 为数组总和的一半，nn 数组元素个数。为共有 n * target 个状态需要被转移，复杂度为 O(n * target)O(n∗target)
//空间复杂度：O(n * target)
