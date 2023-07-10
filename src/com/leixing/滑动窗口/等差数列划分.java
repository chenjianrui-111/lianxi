package com.leixing.滑动窗口;

/**
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * 子数组 是数组中的一个连续序列。
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * 示例 2：
 * 输入：nums = [1]
 * 输出：0
 * 提示：
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 *
 * 双指针
 * 具体的，我们可以枚举 i作为差值为 d 的子数组的左端点，然后通过「双指针」的方式找到当前等差
 * 并最长的子数组的右端点 jj，令区间 [i, j]长度为 len。
 * 那么显然，符合条件的子数组的数量为：
 * cnt = \sum_{k = 3}^{len}countWithArrayLength(k)
 * cnt=
 * k=3
 * ∑
 * len
 *  countWithArrayLength(k)
 * 函数 int countWithArrayLength(int k) 求的是长度为 k 的子数组的数量。
 * 不难发现，随着入参 k的逐步减小，函数返回值逐步增大。
 * 因此上述结果 cnt 其实是一个 首项为 1，末项为 len - 3 + 1，公差为 1 的等差数列的求和结果。直接套用「等差数列求和」公式求解即可。
 */
public class 等差数列划分 {

    public int numberOfArithmeticSlices(int[] nums) {
        int n= nums.length;
        int ans=0;
        for (int i=0;i<n-2;){
            int j=i,d=nums[i + 1] - nums[i];
            while (j+1 <n && nums[j+1] - nums[j] ==d) j++;
            int len= j - i + 1;
            // a1：长度为 len 的子数组数量；an：长度为 3 的子数组数量
            int a1 = 1, an = len - 3 + 1;
            // 符合条件（长度大于等于3）的子数组的数量为「差值数列求和」结果
            int cnt = (a1 + an) * an / 2;
            ans += cnt;
            i = j;
        }
        return ans;
    }
}
