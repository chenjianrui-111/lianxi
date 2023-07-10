package com.daimasuixianglu.huisu.pailiewenti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class 全排列 {
    List<List<Integer>> res=new ArrayList<>();
    LinkedList<Integer> path=new LinkedList<>();
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0){
        return res;
    }
        used=new boolean[nums.length];
        backTracing(nums);
        return res;
    }
    public void backTracing(int[] nums){
        if (path.size()==nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i <nums.length ; i++) {
            if (used[i]) continue;
            used[i]=true;
            path.add(nums[i]);
            backTracing(nums);
            path.removeLast();
            used[i]=false;
        }
    }
}
