package com.xiaochao.回溯算法;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 元素可重不可复选
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。 
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 */
public class 组合总和二 {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track =new LinkedList<>();
    int trackSum = 0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) return res;
        Arrays.sort(candidates);
        backtrack(candidates,0,target);
        return res;
    }
    void backtrack(int[] candidates,int start,int target){
        if (trackSum == target){
            res.add(new LinkedList<>(track));
        }
        if (trackSum > target){
            return;
        }
        for (int i = start; i <candidates.length ; i++) {
            if (i > start && candidates[i] == candidates[i - 1]){
                continue;
            }
            track.addLast(candidates[i]);
            trackSum += candidates[i];
            backtrack(candidates,i+1,target);
            track.removeLast();
            trackSum -= candidates[i];
        }
    }
}
