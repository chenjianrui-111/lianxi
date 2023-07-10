package com.daimasuixianglu.huisu.zuhewenti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class 子集 {
    List<List<Integer>> res=new ArrayList<>();
    LinkedList<Integer> path=new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0){
            res.add(new ArrayList<>());
            return res;
        }
        backTracking(nums,0);
        return res;
    }
    public void backTracking(int[]nums,int startIndex){
        res.add(new ArrayList<>(path));//「遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合」。
        if (startIndex>=nums.length){ //终止条件可不加
            return;
        }
        for (int i = startIndex; i <nums.length ; i++) {
            path.addLast(nums[i]);
            backTracking(nums,i+1);
            path.removeLast();
        }
    }
}
