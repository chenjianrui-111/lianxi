package com.xiaochao.回溯算法;

import java.util.LinkedList;
import java.util.List;

/**
 * 元素⽆重不可复选
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * for 选择 in 选择列表:
 *  # 做选择
 *  将该选择从选择列表移除
 *  路径.add(选择)
 *  backtrack(路径, 选择列表)
 *  # 撤销选择
 *  路径.remove(选择)
 *  将该选择再加⼊选择列表
 */
public class 全排序 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track =new LinkedList<>();
        // 「路径」中的元素会被标记为 true，避免重复使⽤
        boolean[] used =new boolean[nums.length];
        backtrack(nums,track,used);
        return res;
    }
    void backtrack(int[] nums,LinkedList<Integer> track, boolean[] used){
        //base case
        if (track.size() == nums.length){
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i <nums.length ; i++) {
            //排除不合法选择
            if (used[i]){
                continue;
            }
            //作选择
            track.add(nums[i]);
            used[i] = true;
            //进入下一层决策树
            backtrack(nums,track,used);
            //取消选择
            track.removeLast();
            used[i] = false;
        }
    }
}
