package com.leixing.动态规划.序列DP;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * 示例 2：
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * 示例 3：
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 * 提示：
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 * 最长上升子序列（贪心 + 二分）
 * 简单来说，就是在遍历每个数 nums[i] 的同时，维护一个具有单调性的 f[] 数组，其中 f[len] = x代表长度为 len 的最长上升子序列最小结尾元素为 x，
 * 可以证明 f 数组具有单调性（看 前置🧀），因此每次可以通过二分找到小于 nums[i] 的最大下标，来作为 nums[i] 的前一个数。
 */
public class 递增的三元子序列 {
    public boolean increasingTriplet(int[] nums) {
        int n=nums.length;
        int ans=1;
        int [] f=new int[n+1];
        Arrays.fill(f,0x3f3f3f3f);
        for (int i=0;i<n;i++){
            int t=nums[i];
            int l=1,r=i+1;
            while (l < r){
                int mid=l + r >> 1;
                if (f[mid] >=t) r=mid;
                else l=mid + 1;
            }
            f[r] = t;
            ans=Math.max(ans,r);
        }
        return ans >=3;
    }
}
//时间复杂度：O(nlogn)
//空间复杂度：O(n)
