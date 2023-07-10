package com.daimasuixianglu.tanxin.gupiaomaimai;

/**
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 */
public class 跳跃游戏 {
    public boolean canJump(int[] nums) {
        int cover=0;
        if (nums.length ==1) return true;// 只有一个元素，就是能达到
        for (int i = 0; i <=cover ; i++) {// 注意这里是小于等于cover
            cover=Math.max(i+nums[i],cover);
            if (cover>=nums.length-1) return true;// 说明可以覆盖到终点了
        }
        return false;
    }
}
