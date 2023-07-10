package com.daimasuixianglu.huisu.zuhewenti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中
 * 所有可以使数字和为 target 的组合。
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
    List<List<Integer>> res=new ArrayList<>();
    LinkedList<Integer> path=new LinkedList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        backTracking(res,path,candidates, target, 0, 0);
        return res;
    }
    public void backTracking(List<List<Integer>> res,LinkedList<Integer> path,int[] candidates,int target,int sum,int StartIndex){
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = StartIndex; i <candidates.length ; i++) {
            if (sum + candidates[i] > target) break;
            path.add(candidates[i]);
            backTracking(res, path, candidates, target, sum+candidates[i], i+1);
            path.remove(path.size()-1);// 回溯，移除路径 path 最后一个元素
        }
    }
}
