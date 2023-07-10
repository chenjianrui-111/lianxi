package com.daimasuixianglu.beibao.zeroone;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 思路
 * 由于本题是问我们能否将一个数组分成两个「等和」子集。
 * 问题等效于「能否从数组中挑选若干个元素，使得元素总和等于所有元素总和的一半」。
 * 这道题如果抽象成「背包问题」的话，应该是：
 * 我们背包容量为 target=sum/2 ，每个数组元素的「价值」与「成本」都是其数值大小，求我们能否装满背包。
 * 我们直接套用「01 背包」的状态定义：
 * f[i][j] 代表考虑前  i个数值，其选择数字总和不超过 j 的最大价值。
 * 当有了「状态定义」之后，结合我们的「最后一步分析法」，每个数字都有「选」和「不选」两种选择。
 * 因此不难得出状态转移方程：
 */
public class 分割等和子集 {
    public boolean canPartition(int[] nums) {
        int n=nums.length;
        //等和子集的和为总和的一半
        int sum=0;
        for (int i:nums) sum+=i;
        int target=sum/2;
        // 对应了总和为奇数的情况，注定不能被分为两个「等和子集」
        if (target * 2 != sum) return false;
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
//时间复杂度： target为数组总和的一半， n数组元素个数。为共有n*target  个状态需要被转移，复杂度为O(n*target)
//空间复杂度：O(n*target)

//「滚动数组」解法
class Solution04 {
    public boolean canPartition(int[] nums) {
        int n=nums.length;
        int sum=0;
        for (int i:nums) sum+=i;
        int target=sum/2;
        if (target*2!=sum) return false;
        //将物品维度修改为2
        int [][] f=new int[2][target+1];
        // 先处理考虑第 1 件物品的情况
        for (int j = 0; j <target ; j++) {
            f[0][j]=j >=nums[0] ?nums[0] :0;
        }
        // 再处理考虑其余物品的情况
        for (int i = 1; i < n; i++) {
            int t = nums[i];
            for (int j = 0; j <= target; j++) {
                // 不选第 i 件物品，将物品维度的使用加上「&1」
                int no = f[(i-1)&1][j];
                // 选第 i 件物品，将物品维度的使用加上「&1」
                int yes = j >= t ? f[(i-1)&1][j-t] + t : 0;
                f[i&1][j] = Math.max(no, yes);
            }
        }
        // 如果最大价值等于 target，说明可以拆分成两个「等和子集」
        // 将物品维度的使用加上「&1」
        return f[(n-1)&1][target] == target;
    }
}
//时间复杂度： target为数组总和的一半， n数组元素个数。为共有n*target  个状态需要被转移，复杂度为O(n*target)
//空间复杂度：O(target)

//「一维空间优化」解法
//事实上，我们还能继续进行空间优化：只保留代表「剩余容量」的维度，同时将容量遍历方向修改为「从大到小」。
class Solution05 {
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
