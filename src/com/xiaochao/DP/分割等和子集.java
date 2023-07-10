package com.xiaochao.DP;

/**
 * 给你一个 只包含正整数 的 非空 数组 nums .请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * 提示：
 * • 1 <= nums.length <= 200
 * • 1 <= nums[i] <= 100
 */
/**
 * 转换为 01 背包
 * 由于每个数字（数组元素）只能被选一次，而且每个数字选择与否对应了「价值」和「成本」，
 * 求解的问题也与「最大价值」相关。
 * 可以使用「01 背包」的模型来做。
 * 当我们确定一个问题可以转化为「01 背包」之后，就可以直接套用「01 背包」的状态定义进行求解了。
 * 注意，我们积累 DP 模型的意义，就是在于我们可以快速得到可靠的「状态定义」。
 * 在 路径问题 中我教过你通用的 DP 技巧解法，但那是基于我们完全没见过那样的题型才去用
 * 的，而对于一些我们见过题型的 DP 题目，我们应该直接套用（或微调）该模型「状态定义」来做。
 * 我们直接套用「01 背包」的状态定义：
 * f[i][j] 代表考虑前 i 个数值，其选择数字总和不超过 j 的最大价值。
 * 当有了「状态定义」之后，结合我们的「最后一步分析法」，每个数字都有「选」和「不选」两种选择。
 * 因此不难得出状态转移方程：
 * f[i][j] = max(f[i - 1][j],f[i - 1][j - nums[i]] + nums[i]);
 */
public class 分割等和子集 {
    boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum / 2;
        if (target * 2 != sum) return false;
        int[][] f =new int[n][target + 1];
        for (int i = 0; i <= target ; i++) {
            f[0][i] = i >= nums[0] ? nums[0] : 0;
        }
        for (int i = 1; i < n ; i++) {
            int t = nums[i];
            for (int j = 0; j <= target ; j++) {
                //不选第 i 件物品
                int no = f[i - 1][j];
                //选第 i 件物品
                int yes = j >= t ? f[i - 1][j - t] + t : 0;
                f[i][j] = Math.max(no,yes);
            }
        }
        return f[n - 1][target] == target;
    }
}
//• 时间复杂度：target 为数组总和的一半，n 数组元素个数。为共有 n ∗ target 个 状态需要被转移，复杂度为 O(n ∗ target) • 空间复杂度：O(n ∗ target)
/**
 *滚动数组解法
 */
class Solution6{
    public boolean canPartition(int[] nums) {
        int n = nums.length;
//「等和子集」的和必然是总和的一半
        int sum = 0;
        for (int i : nums) sum += i;
        int target = sum / 2;
        if (target * 2 != sum) return false;
        //将物品维度修改为2
        int[][] f = new int[2][target + 1];
        for (int i = 0; i <= target ; i++) {
            f[0][i] = i >= nums[0] ? nums[0] : 0;
        }
        for (int i = 1; i <= n ; i++) {
            int t = nums[i];
            for (int j = 0; j <= target ; j++) {
                int no = f[(i - 1) & 1][j];
                int yes = j >= t ? f[(i - 1) & 1][j - t] + t : 0 ;
                f[i & 1][j] = Math.max(no,yes);
            }
        }
        return f[(n - 1) & 1][target] == target;
    }
}
//• 时间复杂度：target 为数组总和的一半，n 数组元素个数。为共有 n ∗ target 个 状态需要被转移，复杂度为 O(n ∗ target) • 空间复杂度：O(target)
class Solution7 {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
//「等和子集」的和必然是总和的一半
        int sum = 0;
        for (int i : nums) sum += i;
        int target = sum / 2;
// 对应了总和为奇数的情况，注定不能被分为两个「等和子集」
        if (target * 2 != sum) return false;
// 将「物品维度」取消
        int[] f = new int[target + 1];
        for (int i = 0; i < n; i++) {
            int t = nums[i];
// 将「容量维度」改成从大到小遍历
            for (int j = target; j >= 0; j--) {
// 不选第 i 件物品
                int no = f[j];
// 选第 i 件物品
                int yes = j >= t ? f[j-t] + t : 0;
                f[j] = Math.max(no, yes);
            }
        }
// 如果最大价值等于 target，说明可以拆分成两个「等和子集」
        return f[target] == target;
    }
}
//• 时间复杂度：target 为数组总和的一半，n 数组元素个数。为共有 n ∗ target 个 状态需要被转移，复杂度为 O(n ∗ target) • 空间复杂度：O(target)
