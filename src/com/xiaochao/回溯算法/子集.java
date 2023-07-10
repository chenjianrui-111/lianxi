package com.xiaochao.回溯算法;

import java.util.LinkedList;
import java.util.List;

/**
 * 元素⽆重不可复选
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class 子集 {
    List<List<Integer>> res =new LinkedList<>();
    // 记录回溯算法的递归路径
    LinkedList<Integer> track = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums,0);
        return res;
    }
    void backtrack(int[] nums,int start){
        // 前序位置，每个节点的值都是⼀个⼦集
        res.add(new LinkedList<>(track));
        //回溯算法框架
        for (int i = start; i < nums.length ; i++) {
            //做选择
            track.addLast(nums[i]);
            backtrack(nums,i+1);
            //撤销选择
            track.removeLast();
        }
    }
}
