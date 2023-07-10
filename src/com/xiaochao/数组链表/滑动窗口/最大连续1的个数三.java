package com.xiaochao.数组链表.滑动窗口;

/**
 * 给定一个二进制数组 nums 和一个整数 k，如果最多可以把 k 个 0 翻转成 1，请返回数组中连续 1 的最大个数。
 * 示例 1：
 * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 把 2 两个 0 翻转成 1，最长的值为 1 的子数组长度为 6。
 */
public class 最大连续1的个数三 {
    public int longestOnes(int[] nums, int k) {
        int left = 0,right = 0;
        //记录窗口中1出现的次数
        int windowOneCount = 0;
        //记录结果长度
        int res = 0;
        //开始滑动窗口
        while (right < nums.length){
            //扩大窗口
            if (nums[right] == 1){
                windowOneCount++;
            }
            right++;
            while (right - left - windowOneCount > k){
                //当窗口中需要替换的0的数量大于k，缩小窗口
                if (nums[left] == 1){
                    windowOneCount--;
                }
                left++;
            }
            //此时一定是一个合法的窗口，求最大窗口的长度
            res = Math.max(res,right - left);
        }
        return res;
    }
}
