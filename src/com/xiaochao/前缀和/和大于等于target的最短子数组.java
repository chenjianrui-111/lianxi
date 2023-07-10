package com.xiaochao.前缀和;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */

/**
 * 前缀和 + 二分
 * 利用  nums[i] 的数据范围为[1,10^5] ，可知前缀和数组满足「单调递增」。
 * 我们先预处理出前缀和数组 sum（前缀和数组下标默认从 1 开始），对于每个 nums[i] 而言，
 * 假设其对应的前缀和值为 s=sum[i+1] ，我们将 nums[i] 视为子数组的右端点，问题转换为：
 * 在前缀和数组下标 [0,i] 范围内找到满足「值小于等于 s-t 」的最大下标，充当子数组左端点的前一个值。
 * 利用前缀和数组的「单调递增」（即具有二段性），该操作可使用「二分」来做。
 */
public class 和大于等于target的最短子数组 {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length,ans = n + 10;
        int[] sum = new int[n+10];
        for (int i = 1; i <= n  ; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        for(int i = 1;i <= n;i++){
            int s = sum[i], d= s - target;
            int l = 0, r = i;
            while (l < r){
                int mid = l + r + 1 >> 1;
                if (sum[mid] <= d) l =mid;
                else r = mid - 1;
            }
            if (sum[r] <= d) ans = Math.min(ans,i-r);
        }
        return ans == n + 10 ? 0 :ans ;
    }
}
//时间复杂度：预处理前缀和数组的复杂度为O(n) ，遍历前缀和数组统计答案复杂度为 O(logn)。整体复杂度为 O(nlogn)
//空间复杂度：O(n)
