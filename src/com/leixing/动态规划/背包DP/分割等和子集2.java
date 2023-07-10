package com.leixing.动态规划.背包DP;

/**
 * 「滚动数组」解法
 * 在上一讲我们讲到过「01 背包」具有两种空间优化方式。
 * 其中一种优化方式的编码实现十分固定，只需要固定的修改「物品维度」即可
 */
public class 分割等和子集2 {
    public boolean canPartition(int[] nums) {
        int n=nums.length;
        //「等和子集」的和必然是总和的一半
        int sum=0;
        for (int i:nums){
            sum+=i;
        }
        int target=sum / 2;
        if (target * 2!=sum) {
            return false;
        }
        //将「物品维度」修改为 2
        int [][] f=new int[2][target+1];
        // 先处理考虑第 1 件物品的情况
        for (int j=0;j<=target;j++){
            f[0][j]=j >=nums[0] ?nums[0] : 0;
        }

        // 再处理考虑其余物品的情况
        for (int i=1;i<n;i++){
            int t=nums[i];
            for (int j=0;j<=target;j++){
                // 不选第 i 件物品，将物品维度的使用加上「&1」
                int no=f[(i -1)&1][j];
                // 选第 i 件物品，将物品维度的使用加上「&1」
                int yes = j >= t ? f[(i-1)&1][j-t] + t : 0;
                f[i&1][j]=Math.max(no,yes);
            }
        }
        // 如果最大价值等于 target，说明可以拆分成两个「等和子集」
        // 将物品维度的使用加上「&1」
        return f[(n-1)&1][target] == target;
    }
}
//时间复杂度：target 为数组总和的一半，nn 数组元素个数。为共有 n * target 个状态需要被转移，复杂度为 O(n * target)
//空间复杂度：O(target)
