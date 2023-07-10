package com.daimasuixianglu.huisu.zuhewenti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 *给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 * 示例 1：
 * 输入：nums = [4,6,7,7]
 * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 */
public class 递增子序列 {
    List<List<Integer>> res=new ArrayList<>();
    LinkedList<Integer> path=new LinkedList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backTracking(nums,0);
        return res;
    }
    public void backTracking(int[] nums,int startIndex){
        if (path.size()>1){
            res.add(new ArrayList<>(path));
        }
        int[] used=new int[201];
        for (int i = startIndex; i <nums.length ; i++) {
            if (!path.isEmpty() && nums[i]<path.get(path.size()-1) ||(used[nums[i]+100]==1)) continue;
            used[nums[i]+100] = 1;
            path.add(nums[i]);
            backTracking(nums,i+1);
            path.remove(path.size()-1);
        }
    }
}
