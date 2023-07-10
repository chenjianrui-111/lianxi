package com.daimasuixianglu.huisu.zuhewenti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */
public class 子集二 {
    List<List<Integer>> res=new ArrayList<>();
    LinkedList<Integer> path=new LinkedList<>();
    boolean[] used;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length==0){
            res.add(new ArrayList<>(path));
            return res;
        }
        Arrays.sort(nums);
        used=new boolean[nums.length];
        subsetsWithDupHelper(nums,0);
        return res;
    }
    private void subsetsWithDupHelper(int[] nums,int startIndex){
        res.add(new ArrayList<>(path));//「遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合」。
        if (startIndex>=nums.length){
            return;
        }
        // used[i - 1] == true，说明同一树支candidates[i - 1]使用过
        // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
        // 而我们要对同一树层使用过的元素进行跳过
        for (int i = startIndex; i <nums.length ; i++) {
            if (i>0 && nums[i]==nums[i-1] && !used[i-1]){
                continue;
            }
            path.add(nums[i]);
            used[i]=true;
            subsetsWithDupHelper(nums,i+1);
            path.removeLast();
            used[i]=false;
        }
    }
}
