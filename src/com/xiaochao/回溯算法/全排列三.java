package com.xiaochao.回溯算法;

import java.util.LinkedList;
import java.util.List;

/**
 * 标准的全排列算法利⽤ used 数组进⾏剪枝，避免重复使⽤同⼀个元素。如果允许重复使⽤元素的话，直接 放⻜⾃我，去除所有 used 数组的剪枝逻辑就⾏了。
 */
public class 全排列三 {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    public List<List<Integer>> permuteRepeat(int[] nums) {
        backtrack(nums);
        return res;
    }
    // 回溯算法核⼼函数
    void backtrack(int[] nums) {
        // base case，到达叶⼦节点
        if (track.size() == nums.length) {
            // 收集叶⼦节点上的值
            res.add(new LinkedList(track));
            return;
        }
        // 回溯算法标准框架
        for (int i = 0; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            // 进⼊下⼀层回溯树
            backtrack(nums);
            // 取消选择
            track.removeLast();
        }
    }
}
