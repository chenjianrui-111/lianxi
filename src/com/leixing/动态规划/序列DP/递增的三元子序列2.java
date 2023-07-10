package com.leixing.动态规划.序列DP;

/**
 * 优化 : 定长上升子序列（贪心）
 * 利用本题只需要我们判定是否存在长度为 3 的上升子序列，而不需要回答 LIS 最大长度。
 * 我们可以对 f 数组进行优化：使用有限变量进行替换（将 ff 数组的长度压缩为 2），数组含义不变，f[1]=x 代表长度为 1 的上升子序列最小结尾元素为 x，f[2]=y 代表长度为 2 的上升子序列的最小结尾元素为 y。
 * 从前往后扫描每个 nums[i]，每次将 nums[i] 分别与 f[1] 和 f[2] 进行比较，如果能够满足 nums[i] > f[2]，代表 nums[i] 能够接在长度为 2 的上升子序列的后面，直接返回 True，否则尝试使用 nums[i] 来更新 f[1] 和 f[2]。
 * 这样，我们只使用了有限变量，每次处理 nums[i] 只需要和有限变量进行比较，时间复杂度为 O(n)，空间复杂度为 O(1)。
 */
public class 递增的三元子序列2 {
    public boolean increasingTriplet(int[] nums) {
        int n=nums.length;
        long [] f=new long[3];
        f[1] = f[2]= (int) 1e19;
        for (int i=0;i<n;i++){
            int t=nums[i];
            if (f[2] <t) return true;
            else if (f[1] < t && t<f[2]) f[2]=t;
            else if (f[1] > t) f[1]=t;
        }
        return false;
    }
}
//时间复杂度：O(n)
//空间复杂度：O(1)
